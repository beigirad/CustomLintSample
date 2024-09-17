package ir.beigirad.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.google.auto.service.AutoService

@AutoService(value = [IssueRegistry::class])
class LintIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(
        LegacyLifecycleDetector.ISSUE,
    )

    override val api: Int = CURRENT_API

    override val minApi: Int = 7

    override val vendor: Vendor = Vendor(vendorName = "Beigirad Rules")
}