package harpritIvanUday.example.gameshed.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GameDetailsViewModel: ViewModel() {
    lateinit var userData: HashMap<String, Any>
    private val currentUser = Firebase.firestore.collection("users").document(FirebaseAuth.getInstance().uid.toString())
    init {
        currentUser.get().addOnSuccessListener {
            if(it.exists()){
                userData = it.data as HashMap<String, Any>
            }
            userData = it.data as HashMap<String, Any>
        }
    }
    fun addToFavorite(gameID: Int) {
        currentUser
            .update("favorites", FieldValue.arrayUnion(gameID))
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }
    }
    fun removeFromFavorite(gameID: Int) {
        currentUser
            .update("favorites", FieldValue.arrayRemove(gameID))
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }
    }

}