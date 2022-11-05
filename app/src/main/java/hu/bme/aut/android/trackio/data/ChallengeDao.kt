package hu.bme.aut.android.trackio.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChallengeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChallenge(challenge: Challenge)

    @Query("SELECT * FROM challanges_table ORDER BY id ASC")
    fun readAllDate(): LiveData<List<Challenge>>
}