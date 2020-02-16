package com.nimbusframework.nimbuscore.persisted

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NimbusState(
        val projectName: String = "",
        val cloudProvider: CloudProvider = CloudProvider.AWS,
        val compilationTimeStamp: String = "",
        val afterDeployments: MutableMap<String, MutableList<String>> = mutableMapOf(),
        //Stage -> Bucket -> LocalFile -> RemoteFile
        val fileUploads: MutableMap<String, MutableMap<String, MutableList<FileUploadDescription>>> = mutableMapOf(),
        val exports: MutableMap<String, MutableList<ExportInformation>> = mutableMapOf(),
        val handlerFiles: MutableSet<HandlerInformation> = mutableSetOf(),
        val assemble: Boolean = false
)