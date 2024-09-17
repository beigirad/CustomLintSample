package ir.beigirad.lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

class LegacyLifecycleDetectorTest {
    private val initVariableCode = kotlin(
        """
        |package ir.beigirad.lint
        |
        |class Test1 {
        |    override fun initVariables(savedInstanceState: Bundle?) {
        |    }
        |
        |    override fun initVariables() {
        |    }
        |    
        |    override fun initializedVariables() {
        |    }
        |}""".trimMargin()
    )
    private val initUICode = kotlin(
        """
        |package ir.beigirad.lint
        |
        |class Test1 {
        |    override fun initUI(savedInstanceState: Bundle?) {
        |    }
        |        
        |    override fun initUI() {
        |    }
        |   
        |    override fun initializedUI() {
        |    }
        |}""".trimMargin()
    )

    private val initObservableCode = kotlin(
        """
        |package ir.beigirad.lint
        |
        |class Test1 {
        |    override fun initObservers() {
        |    }
        |}""".trimMargin()
    )

    @Test
    fun `initVariables has been detected`() {
        lint()
            .files(initVariableCode)
            .issues(LegacyLifecycleDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectWarningCount(3)
            .expectErrorCount(0)
            .expect(
                """
                |src/ir/beigirad/lint/Test1.kt:4: Warning: Replace initVariables with original lifecycle callbacks: [onCreate(...), onViewCreated(...)] [Legacy Lifecycle Deprecation]
                |    override fun initVariables(savedInstanceState: Bundle?) {
                |    ^
                |src/ir/beigirad/lint/Test1.kt:7: Warning: Replace initVariables with original lifecycle callbacks: [onCreate(...), onViewCreated(...)] [Legacy Lifecycle Deprecation]
                |    override fun initVariables() {
                |    ^
                |src/ir/beigirad/lint/Test1.kt:10: Warning: Replace initializedVariables with original lifecycle callbacks: [onCreate(...), onViewCreated(...)] [Legacy Lifecycle Deprecation]
                |    override fun initializedVariables() {
                |    ^
                |0 errors, 3 warnings""".trimMargin()
            )
    }

    @Test
    fun `initUI has been detected`() {
        lint()
            .files(initUICode)
            .issues(LegacyLifecycleDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectWarningCount(3)
            .expectErrorCount(0)
            .expect(
                """
                |src/ir/beigirad/lint/Test1.kt:4: Warning: Replace initUI with original lifecycle callbacks: [onCreate(...), onViewCreated(...)] [Legacy Lifecycle Deprecation]
                |    override fun initUI(savedInstanceState: Bundle?) {
                |    ^
                |src/ir/beigirad/lint/Test1.kt:7: Warning: Replace initUI with original lifecycle callbacks: [onCreate(...), onViewCreated(...)] [Legacy Lifecycle Deprecation]
                |    override fun initUI() {
                |    ^
                |src/ir/beigirad/lint/Test1.kt:10: Warning: Replace initializedUI with original lifecycle callbacks: [onCreate(...), onViewCreated(...)] [Legacy Lifecycle Deprecation]
                |    override fun initializedUI() {
                |    ^
                |0 errors, 3 warnings""".trimMargin()
            )
    }

    @Test
    fun `initObservers has been detected`() {
        lint()
            .files(initObservableCode)
            .issues(LegacyLifecycleDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectWarningCount(1)
            .expectErrorCount(0)
            .expect(
                """
                |src/ir/beigirad/lint/Test1.kt:4: Warning: Replace initObservers with original lifecycle callbacks: [onCreate(...), onViewCreated(...)] [Legacy Lifecycle Deprecation]
                |    override fun initObservers() {
                |    ^
                |0 errors, 1 warnings""".trimMargin()
            )
            .verifyFixes()
            .expectFixDiffs(
                """
                |Fix for src/ir/beigirad/lint/Test1.kt line 4: Delete:
                |@@ -4 +4
                |-     override fun initObservers() {
                |-     }""".trimMargin()
            )
    }
}