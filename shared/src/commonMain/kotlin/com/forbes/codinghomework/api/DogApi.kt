package com.forbes.codinghomework.api

import com.forbes.codinghomework.interfaces.ApiCall
import com.forbes.codinghomework.interfaces.DataResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DogApi(
    private val client: HttpClient
) {

    suspend fun getDogs(dogBreed: DogBreed): DogResponse =
        client.get(
            "https://dog.ceo/api/breed/${dogBreed.breedName}/images/random/$MIN_IMAGE_RETURN"
        ).body()

    suspend fun getDogs(dogBreed: DogBreed, count: Int): DogResponse {
        if (count < 1) {
            error("Count for getDogs() call must be 1 or greater")
        }

        return client.get(
            "https://dog.ceo/api/breed/${dogBreed.breedName}/images/random/$count"
        ).body()
    }

    companion object {
        const val MIN_IMAGE_RETURN = 5
    }
}

sealed class DogCall: ApiCall {
    class GetDogs(val dogBreed: DogBreed): DogCall()
    class GetNumerousDogs(
        val dogBreed: DogBreed,
        val count: Int
    ): DogCall()
}

@Serializable
data class DogResponse(
    @SerialName("message")
    val dogImageUrls: List<String>
): DataResponse

/**
 * If you want to add a different breed to see your favorite dog pictures check out the list
 * https://dog.ceo/dog-api/breeds-list
 */
enum class DogBreed(val breedName: String) {
    Boxer("boxer")
}