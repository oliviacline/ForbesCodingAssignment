package com.forbes.codinghomework

import com.forbes.codinghomework.api.DogCall
import com.forbes.codinghomework.interfaces.ApiCall
import com.forbes.codinghomework.interfaces.DataResponse
import com.forbes.codinghomework.repository.DogRepository
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

/**
 * OperatorSwitchboard
 *
 * So named as it acts like the human operated call centers that used to direct our phone calls.
 *
 * A caller will:
 * - Invoke the operator
 * - Tell the operator what it is trying to reach
 * - Then the operator will direct call to the proper repository
 *
 * Seems like a redundant layer, but there may be weird interpretation steps we will need to be
 * aware of as we take calls from Android and iOS. Generalizing always seems like fun until it isn't
 * so we have this here to allow for one off cases to exist.
 */
object OperatorSwitchboard {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json( Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    private val dogRepository = DogRepository(httpClient)

    /**
     * The initial idea here was to:
     * - Create a single entry point for API calls to go through
     * - Process the call through the Repository and send a KmmResult back up
     * - Allow the platform VM to see if the result was a success or failure and react
     *
     * However, I ran into issues when dealing with the Sealed classes and attempting to interpret
     * them on the Swift side.
     *
     * You will want to cast the response with the one you were expecting to access the data.
     */
    fun fetchData(request: ApiCall): DataResponse =
        when(request) {
            is DogCall -> runBlocking {
                when(val data = dogRepository.getDogData(request)) {
                    is KmmResult.Success -> {
                        data.value
                    }
                    is KmmResult.Failure -> {
                        error("Normally this error would be pushed up to notify the user in the UI" +
                                "however, sealed classes and Swift are not behaving.")
                    }
                }
            }
            else -> { error("Unknown API call sent to Operator. Please check the call and try again.") }
        }
}