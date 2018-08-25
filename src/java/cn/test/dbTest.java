package java.cn.test;

public class dbTest {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://47.94.192.152:3306/webmap?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
        String user = "root";
        String password = "Geniuslyu#970331";


        //建表
//            Class.forName(driver);
//            Connection con = DriverManager.getConnection(url, user, password);
//            Statement stat = con.createStatement();
//             stat.executeUpdate("create table polygon_android(_id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR (50),location VARCHAR(200), time DATETIME(6),updatetype int);");
//             stat.executeUpdate("create table polygon_web(_id int primary key AUTO_INCREMENT, name varchar(50), location varchar(200), time datetime(6),updatetype int);");
//             stat.executeUpdate("create table delpolygon_cache(_id int primary key, updatetype Integer)");
//             stat.executeUpdate("create table circle_android(_id int primary key AUTO_INCREMENT,name varchar(50),location varchar(200), time datetime(6),updatetype int)");
//             stat.close();
//             con.close();




        //数据库连接测试
//        try {
//            Class.forName(driver);
//            Connection con = DriverManager.getConnection(url, user, password);
//            if (!con.isClosed()) {
//                System.out.println("数据库连接成功");
//            }
//            Statement statement = con.createStatement();
//            String sql = "select * from user;";
//            ResultSet resultSet = statement.executeQuery(sql);
//            String username;
//            while (resultSet.next()) {
//                username = resultSet.getString("username");
//                System.out.println("姓名：" + username);
//            }
//            resultSet.close();
//            con.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("数据库驱动没有安装");
//
//        } catch (SQLException e) {
//            System.out.println("数据库连接失败");
//        }



    }
}










//
//    private static String DB_NAME="polygon.db";
//    private static int  DB_VERSION=1;
//    public DBOpenHelper(Context context ) {
//        super(context, DB_NAME, null,DB_VERSION );
//    }
//    //数据库创建的时候调用，去创建一张表
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        //建张表  1个字段  ，_id为主键 自增长
//        db.execSQL("create table polygon_android(_id Integer primary key autoincrement,name varchar(50),location varchar(200), time datetime(6),updatetype Integer)");
//        db.execSQL("create table polygon_web(_id Integer primary key autoincrement, name varchar(50), location varchar(200), time datetime(6),updatetype Integer)");
//        db.execSQL("create table delpolygon_cache(_id Integer primary key, updatetype Integer)");
//        db.execSQL("create table circle_android(_id Integer primary key autoincrement,name varchar(50),location varchar(200), time datetime(6),updatetype Integer)");
////        db.execSQL("create table polygon_point (_id Integer primary key autoincrement,listnum Integer,listid Integer,lat varchar(20),lon varchar(20))");
////        db.execSQL("create table polygon_point"+1+"(listid Integer primary key autoincrement, _id Integer,lat varchar(20),lon varchar(20),FOREIGN KEY(_id)REFERENCES polygon_list(_id))");
//    }
//
//    //数据库版本提升的时候调用,比如软件升级时，要在原本的数据库一个表里面新增加一个字段，或者增加一张表，就在这里面修改数据库
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        //
//    }

