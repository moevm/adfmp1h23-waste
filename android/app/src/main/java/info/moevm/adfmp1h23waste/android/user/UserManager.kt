package info.moevm.adfmp1h23waste.android.user

class UserManager private constructor() {

    var user: User? = null

    companion object {
        val instance: UserManager = UserManager()
    }
}
