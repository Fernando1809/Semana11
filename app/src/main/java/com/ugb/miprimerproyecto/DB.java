package com.ugb.miprimerproyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    Context miContext;
    static String nombreDB = "db_amigos";
    static String tblAmigos = "CREATE TABLE tblamigos(idAmigo integer primary key autoincrement, nombre text, telefono text, direccion text, email text, urlPhoto text)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreDB, factory, version); //CREATE DATABASE db_amigos; -> MySQL, SQL Server, Oracle, PostGreeSQL, other...
        miContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblAmigos);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NO, porque es para migrar o actualizar a una nueva version...
    }
    public Cursor administracion_amigos(String accion, String[] datos){
        Cursor datosCursor = null;
        try {
            SQLiteDatabase sqLiteDatabaseW = getWritableDatabase();
            SQLiteDatabase sqLiteDatabaseR = getReadableDatabase();
            switch (accion) {
                case "consultar":
                    datosCursor = sqLiteDatabaseR.rawQuery("select * from tblamigos order by nombre", null);
                    break;
                case "nuevo":
                    sqLiteDatabaseW.execSQL("INSERT INTO tblamigos(nombre,telefono,direccion,email,urlPhoto) VALUES ('" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "','" + datos[5] + "')");
                    break;
                case "modificar":
                    sqLiteDatabaseW.execSQL("UPDATE tblamigos SET nombre='" + datos[1] + "',telefono='" + datos[2] + "',direccion='" + datos[3] + "',email='" + datos[4] + "',urlPhoto='" + datos[5] + "' WHERE idAmigo='" + datos[0] + "'");
                    break;
                case "eliminar":
                    sqLiteDatabaseW.execSQL("DELETE FROM tblamigos WHERE idAmigo='" + datos[0] + "'");
                    break;
            }
            return datosCursor;
        }catch (Exception e){
            Toast.makeText(miContext, "Error en la administracion de la BD "+ e.getMessage(), Toast.LENGTH_LONG).show();
            return datosCursor;
        }
    }
}
