package com.forbes.codinghomework.repository

import com.forbes.codinghomework.KmmResult
import com.forbes.codinghomework.api.DogApi
import com.forbes.codinghomework.api.DogCall
import com.forbes.codinghomework.api.DogResponse
import io.ktor.client.*

/**
 * In lieu of having a full out repository pattern, this exists.
 *
 * Normally this would have some kind of store that it would check to verify
 *
 * Results are derived here given that normally a database hit could happen here.
 */
class DogRepository(
    private val client: HttpClient
) {

    private val dogApi = DogApi(client)

    suspend fun getDogData(request: DogCall): KmmResult<DogResponse> =
        when (request) {
            is DogCall.GetDogs -> {
                try {
                    KmmResult.Success(dogApi.getDogs(request.dogBreed))
                } catch (e: Exception) {
                    KmmResult.Failure(e)
                }
            }
            is DogCall.GetNumerousDogs -> {
                try {
                    KmmResult.Success(dogApi.getDogs(request.dogBreed, request.count))
                } catch (e: Exception) {
                    KmmResult.Failure(e)
                }
            }
        }
}