package hu.bme.aut.android.trackio.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.aut.android.trackio.model.Challenge

@Dao
interface ChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChallenge(challenge: Challenge)

    @Query("SELECT * FROM challanges_table ORDER BY id ASC")
    fun readAllDate(): LiveData<List<Challenge>>

    @Delete
    suspend fun deleteChallenge(challenge: Challenge)
}