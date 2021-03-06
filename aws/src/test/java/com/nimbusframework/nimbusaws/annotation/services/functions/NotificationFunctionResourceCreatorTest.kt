package com.nimbusframework.nimbusaws.annotation.services.functions

import com.nimbusframework.nimbusaws.CompileStateService
import com.nimbusframework.nimbusaws.annotation.processor.FunctionInformation
import com.nimbusframework.nimbusaws.annotation.services.FunctionEnvironmentService
import com.nimbusframework.nimbusaws.annotation.services.ResourceFinder
import com.nimbusframework.nimbusaws.cloudformation.CloudFormationFiles
import com.nimbusframework.nimbusaws.cloudformation.resource.notification.SnsTopicResource
import com.nimbusframework.nimbuscore.persisted.NimbusState
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import javax.annotation.processing.Messager
import javax.annotation.processing.RoundEnvironment

class NotificationFunctionResourceCreatorTest : AnnotationSpec() {

    private lateinit var notificationStoreFunctionResourceCreator: NotificationFunctionResourceCreator
    private lateinit var roundEnvironment: RoundEnvironment
    private lateinit var cfDocuments: MutableMap<String, CloudFormationFiles>
    private lateinit var nimbusState: NimbusState
    private lateinit var functionEnvironmentService: FunctionEnvironmentService
    private lateinit var compileStateService: CompileStateService
    private lateinit var resourceFinder: ResourceFinder
    private lateinit var messager: Messager

    @BeforeEach
    fun setup() {
        nimbusState = NimbusState()
        cfDocuments = mutableMapOf()
        roundEnvironment = mockk()
        resourceFinder = mockk()
        messager = mockk(relaxed = true)
        compileStateService = CompileStateService("models/NotificationTopic.java", "handlers/NotificationHandlers.java")
        functionEnvironmentService = FunctionEnvironmentService(cfDocuments, nimbusState)
    }

    @Test
    fun correctlyProcessesNotificationFunctionAnnotation() {
        compileStateService.compileObjects {
            every { resourceFinder.getNotificationTopicResource(any(), any(), any()) } returns SnsTopicResource("notificationTopic", nimbusState, "dev")
            notificationStoreFunctionResourceCreator = NotificationFunctionResourceCreator(cfDocuments, nimbusState, it, setOf(), messager, resourceFinder)
            val classElem = it.elementUtils.getTypeElement("handlers.NotificationHandlers")
            val funcElem = classElem.enclosedElements[1]
            val results = notificationStoreFunctionResourceCreator.handleElement(funcElem, functionEnvironmentService)
            cfDocuments["dev"] shouldNotBe null

            val resources = cfDocuments["dev"]!!.updateTemplate.resources
            resources.size() shouldBe 6

            results.size shouldBe 1

            verify {messager wasNot Called}
        }
    }

}