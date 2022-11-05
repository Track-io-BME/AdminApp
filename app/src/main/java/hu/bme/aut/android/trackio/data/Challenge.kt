package hu.bme.aut.android.trackio.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.Duration

@Entity(tableName = "challanges_table")
data class Challenge(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val distance: Float,
    val sportType : SportType,
    val duration: Int,
    val startdate : Long
){
    enum class SportType {
        WALKING, RUNNING, CYCLING;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): SportType? {
                var ret: SportType? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(category: SportType): Int {
                return category.ordinal
            }
        }
    }
}
