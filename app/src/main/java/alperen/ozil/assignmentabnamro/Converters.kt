package alperen.ozil.assignmentabnamro

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun fromStringToOwner(value: String?): Owner? {
        return value?.let { Owner(it) }
    }

    @TypeConverter
    fun fromOwnerToString(owner: Owner?): String? {
        return owner?.avatar_url
    }
}