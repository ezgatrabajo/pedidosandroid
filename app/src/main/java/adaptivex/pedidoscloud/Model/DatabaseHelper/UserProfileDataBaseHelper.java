package adaptivex.pedidoscloud.Model.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import adaptivex.pedidoscloud.Config.Configurador;

/**
 * Created by ezequiel on 30/05/2016.
 */
public class UserProfileDataBaseHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = Configurador.DBName;
    public static final String TABLE_NAME = "userprofiles";
    public static final int    DB_VERSION = Configurador.DBVersion ;
    public static final String ID        = "id";
    public static final String NOMBRE    = "nombre";
    public static final String APELLIDO  = "apellido";
    public static final String CALLE   = "calle";
    public static final String NRO     = "nro";
    public static final String PISO    = "piso";
    public static final String CONTACTO    = "contacto";
    public static final String TELEFONO    = "telefono";


    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID          + "  integer not null," +
            NOMBRE      + "  text, " +
            APELLIDO    + "  text, " +
            CALLE       + "  text, " +
            NRO         + "  text, " +
            PISO        + "  text, " +
            CONTACTO    + "  text, " +
            TELEFONO    + "  text " +
            " )";

    public UserProfileDataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.println(Log.INFO,"DatabaseHelper: ",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.println(Log.INFO,"DatabaseHelper: ",DROP_TABLE);
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

}
