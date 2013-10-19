package tc.oc.commons.core.util;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.Instant;

import com.google.common.collect.Lists;
import com.mongodb.DBObject;

import javax.annotation.Nullable;

public class DBO {
    public static boolean getBoolean(DBObject dbo, String field, boolean def) {
        Object value = dbo.get(field);
        if (value == null) {
            return def;
        } else if (value instanceof Number) {
            return ((Number) value).intValue() > 0;
        } else if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            throw new IllegalArgumentException("can't coerce to bool: " + value.getClass());
        }
    }

    public static @Nullable Byte getByte(DBObject dbo, String field) {
        Object value = dbo.get(field);
        if (value == null) {
            return null;
        } else if (value instanceof Byte) {
            return (byte) value;
        } else if (value instanceof Number) {
            return ((Number) value).byteValue();
        } else if (value instanceof String) {
            return Byte.parseByte((String) value);
        } else if (value instanceof Boolean) {
            return (byte) (((Boolean) value) ? 1 : 0);
        }
        return null;
    }

    public static int getInt(DBObject dbo, String field, int def) {
        try {
            return getInt(dbo, field);
        } catch (Throwable t) {
            return def;
        }
    }

    public static int getInt(DBObject dbo, String field) {
        Object value = dbo.get(field);
        if (value == null) {
            throw new NullPointerException("can't be null");
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof Boolean) {
            return ((Boolean) value) ? 1 : 0;
        } else {
            if (value instanceof String) {
                try {
                    return Integer.parseInt((String) value);
                } catch (Exception e) {
                }
            }
            throw new IllegalArgumentException("can't coerce to int: " + value.getClass());
        }
    }

    public static @Nullable Integer getInteger(DBObject dbo, String field) {
        Object value = dbo.get(field);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof Boolean) {
            return ((Boolean) value) ? 1 : 0;
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return null;
    }

    public static double getDouble(DBObject dbo, String field, double def) {
        double result = getDouble(dbo, field);
        if (result != Double.NaN) {
            return result;
        } else {
            return def;
        }
    }

    public static double getDouble(DBObject dbo, String field) {
        Object value = dbo.get(field);
        if (value == null) {
            return Double.NaN;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else {
            try {
                return Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                return Double.NaN;
            }
        }
    }

    public static String getString(DBObject dbo, String field, String def) {
        String result = getString(dbo, field);
        if (result != null) {
            return result;
        } else {
            return def;
        }
    }

    public static String getString(DBObject dbo, String field) {
        Object value = dbo.get(field);
        if (value == null) {
            return null;
        } else {
            return value.toString();
        }
    }

    public static ObjectId getObjectId(DBObject dbo, String field, ObjectId def) {
        ObjectId result = getObjectId(dbo, field);
        if (result == null) {
            return def;
        } else {
            return result;
        }
    }

    public static ObjectId getObjectId(DBObject dbo, String field) {
        return ObjectId.massageToObjectId(dbo.get(field));
    }

    public static List<String> getStringList(DBObject dbo, String field) {
        Object value = dbo.get(field);
        List<String> result = Lists.newArrayList();
        if (value != null && value instanceof List<?>) {
            for (Object el : (List<?>) value) {
                result.add(el.toString());
            }
        }
        return result;
    }

    public static @Nullable Instant getTime(DBObject dbo, String field) {
        Object value = dbo.get(field);
        if (value != null) {
            if (value instanceof Date) {
                return new Instant(value);
            }
        }
        return null;
    }
}
