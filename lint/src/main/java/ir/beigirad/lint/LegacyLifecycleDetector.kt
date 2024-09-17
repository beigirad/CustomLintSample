package ir.beigirad.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.intellij.lang.jvm.JvmModifier
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

class LegacyLifecycleDetector : Detector(), Detector.UastScanner {
    private val debugMode = false
    private val methodsName =
        listOf("initVariables", "initializedVariables", "initUI", "initializedUI", "initObservers")

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UClass::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitClass(node: UClass) {
                val methods = node.methods.filter { it.name in methodsName && !it.hasModifier(JvmModifier.PRIVATE) }

                printer("${node.qualifiedName} ${methods.map { it.name }}")
                methods.forEach { method ->
                    context.report(
                        issue = ISSUE,
                        location = context.getLocation(method),
                        message = "Replace ${method.name} with original lifecycle callbacks: [onCreate(...), onViewCreated(...)]",
                        quickfixData = fix()
                            .replace()
                            .build()
                    )
                }
            }
        }
    }

    private fun printer(message: String) {
        if (debugMode)
            println("Detector: $message")
    }

    companion object {
        @JvmField
        val ISSUE = Issue.create(
            id = "Legacy Lifecycle Deprecation",
            briefDescription = "Find legacy lifecycle usages",
            explanation = "Legacy lifecycle must be deleted step by step",
            category = Category.LINT,
            priority = 6,
            severity = Severity.WARNING,
            implementation = Implementation(
                LegacyLifecycleDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}