package tc.oc.commons.core.util;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class MongoUtils {
    public static DBObject setKeyValue(String key, Object value) {
        return new BasicDBObject("$set", new BasicDBObject(key, value));
    }

    public static DBObject fields(String... keys) {
        DBObject obj = new BasicDBObject();
        for (String key : keys) {
            obj.put(key, true);
        }
        return obj;
    }

    public static DBObject exists() {
        return new BasicDBObject("$exists", true);
    }

    public static DBObject range(Date start, Date end) {
        return BasicDBObjectBuilder.start().add("$gt", start).add("$lte", end).get();
    }
}
