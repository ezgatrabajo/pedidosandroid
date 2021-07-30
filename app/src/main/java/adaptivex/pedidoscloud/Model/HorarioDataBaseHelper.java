package adaptivex.pedidoscloud.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import adaptivex.pedidoscloud.Config.Configurador;

/**
 * Created by ezequiel on 30/05/2016.
 */
public class HorarioDataBaseHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = Configurador.DBName;
    public static final String TABLE_NAME = "horarios";
    public static final int DB_VERSION = Configurador.DBVersion;
    public static final String CAMPO_ID             = "id";
    public static final String CAMPO_DIA            = "dia";
    public static final String CAMPO_APERTURA       = "apertura";
    public static final String CAMPO_CIERRE         = "cierre";
    public static final String CAMPO_OBSERVACIONES  = "observaciones";
    public static final String CAMPO_DATE           = "date";
    public static final String CAMPO_ROOT           = "horario";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME
            + " (" +
            CAMPO_ID + " integer not null," +
            CAMPO_DIA           + " integer not null, " +
            CAMPO_APERTURA      + " date not null, " +
            CAMPO_CIERRE        + " date not null, " +
            CAMPO_OBSERVACIONES + " text " +
            ")";

    public HorarioDataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

}
