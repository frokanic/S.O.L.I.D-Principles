package singleresponsibility

fun main() {

}

/** USER AUTHENTICATION EXAMPLE */

/** Correct implementation
 *      As we can see below, we have a few classes and interfaces.
 *
 *  1. AuthenticationOutcome: Its responsibility is to define all the possible
 *      outcomes of the authentication attempt. Its only reason to change is
 *      if the outcomes change (for example, return some data that we deem
 *      important at Success).
 *
 *  2. UserAuthRepository: This class acts as an intermediary between the
 *      business and data layers. Its only reason to change would be if the
 *      authentication process did so. For example, adding a fallback mechanism.
 *
 *  3. NetworkDataSource: Handles direct communication with the network
 *      (though in our case an api class was not created). Its role is to
 *      manage direct communication with the network. Its reason to change
 *      would be modifications in how the application needs to interact with
 *      the network or handle errors.
 */

sealed interface AuthenticationOutcome {
    data object Success : AuthenticationOutcome
    data class Error(val errorMessage: String) : AuthenticationOutcome
}

class UserAuthRepository(
    private val dataSource: NetworkDataSource
) {
    fun authCredentials(
        username: String,
        password: String
    ): AuthenticationOutcome =
        dataSource.auth(username, password)
}

class NetworkDataSource {
    fun auth(username: String, password: String): AuthenticationOutcome {
        /** Mimicking an api response */
        val apiStatusCode = 200

        return if (apiStatusCode == 200) {
            AuthenticationOutcome.Success
        } else {
            AuthenticationOutcome.Error("Authentication failed")
        }
    }
}

/** Incorrect implementation
 *
 *  Although not visible at first, the UserAuthRepositoryIncorrect does,
 *  indeed, violate, the single responsibility principle. This is because it
 *  both acts as an intermediary layer between the data and domain layer,
 *  as well as directly interact with the network. This gives it more that
 *  one reason to change, thus, violating the single responsibility principle.
 */

class UserAuthRepositoryIncorrect(
    private val dataSource: NetworkDataSource
) {
    fun authCredentials(
        username: String,
        password: String
    ): AuthenticationOutcome {
        /** Mimicking an api response */
        val apiStatusCode = 200

        return if (apiStatusCode == 200) {
            AuthenticationOutcome.Success
        } else {
            AuthenticationOutcome.Error("Authentication failed")
        }
    }
}