package es.weso;

import scala.collection.JavaConverters;

/**
 * Created by Labra on 16/11/2016.
 */
public class ScalaConverters {

    public static <T> java.util.List<T> asJava(scala.collection.Seq<T> scalaList) {
        return JavaConverters.seqAsJavaListConverter(scalaList).asJava();
    }

}
