import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.itemData

class database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "my_table"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME ($COLUMN_NAME TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(name: String) {
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        writableDatabase.insert(TABLE_NAME, null, values)
    }
    fun removeData(name: String) {
        writableDatabase.delete(TABLE_NAME, "$COLUMN_NAME = ?", arrayOf(name))
    }

    @SuppressLint("Range")
    fun getAllData(): List<itemData> {
        val data = mutableListOf<itemData>()
        val cursor = readableDatabase.query(TABLE_NAME, arrayOf(COLUMN_NAME), null, null, null, null, null)
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            data.add(itemData(name))
        }
        cursor.close()
        return data
    }
}
