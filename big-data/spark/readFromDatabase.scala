//add database input options separately 
val df = sqlContext.read.format("jdbc").
  option("url", "jdbc:mysql://localhost:3306/data").
  option("dbtable", "sample_data_1").
  option("driver", "com.mysql.jdbc.Driver").
  option("user", "root").
  option("password", "pw").
  load();

//add database input options as Map
val df= sqlContext.read.format("jdbc").
  options(Map(
      "url" -> "jdbc:mysql://localhost:3306/data", 
      "dbtable" -> "sample_data_1", 
      "driver" -> "com.mysql.jdbc.Driver", 
      "user" -> "root", 
      "password" -> "pw")).
  load();