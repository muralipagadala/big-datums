//create properties object
val prop = new java.util.Properties
prop.setProperty("driver", "com.mysql.jdbc.Driver")
prop.setProperty("user", "root")
prop.setProperty("password", "pw") 

//jdbc mysql url - destination database is named "data"
val url = "jdbc:mysql://localhost:3306/data"

//destination database table 
val table = "sample_data_table"

//write data from spark dataframe to database
df.write.mode("append").jdbc(url, table, prop)