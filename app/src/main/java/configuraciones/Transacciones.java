package configuraciones;

public class Transacciones {
    //Nombre de la base de datos
    public static final String NameDatabase = "PM01DB";

    //Creacion de las tablas de la base de datos
    public static final String tablaempleados = "empleados";
       //Creacion de los atributos
        public static final String id = "id";
        public static final String nombres = "nombres";
        public static final String apellidos = "apellidos";
        public static final String edad = "edad";
        public static final String correo = "correo";



        //Transacciones DDL
        public static final String createTableEmpleados = "CREATE TABLE " + tablaempleados +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT)";

        public static final String dropTableEmpleados = "DROP TABLE IF EXIST" + tablaempleados;
}
