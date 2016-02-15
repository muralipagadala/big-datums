// run on Spark 1.6.0
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

val conf = new SparkConf()
val sc = new SparkContext(conf)
val sqlContext = new SQLContext(sc)

/*  sample.json

	"user": "gT35Hhhre9m",
	"dates": ["2016-01-29", "2016-01-28"],
	"status": "OK",
	"reason": "some reason",
	"content": [{
		"foo": 123,
		"bar": "val1"
	}, {
		"foo": 456,
		"bar": "val2"
	}, {
		"foo": 789,
		"bar": "val3"
	}, {
		"foo": 124,
		"bar": "val4"
	}, {
		"foo": 126,
		"bar": "val5"
	}]
}
*/

// json file to df
val df = sqlContext.read.json("sample.json")

// select and explode "content"
val dfContent = df.select(explode(df("content")))`

//rename "col" to "content"
val dfContent = df.select(explode(df("content"))).toDF("content")

//extracting fields in struct to new DataFrame
val dfFooBar = dfContent.select("content.foo", "content.bar")

// output
dfFooBar.show
