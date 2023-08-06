package com.example.uas_10120905_johndypanca.ui.api

import com.example.uas_10120905_johndypanca.ui.agent.AgentResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchAgent {
        @GET("/v1/agents?isPlayableCharacter=true")
        suspend fun getData(): Response<AgentResponse>
}