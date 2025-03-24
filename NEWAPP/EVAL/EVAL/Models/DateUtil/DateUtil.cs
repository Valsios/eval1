namespace EVAL.Models.DateUtil;

using System;
using System.Text.Json;
using System.Text.Json.Serialization;

public class DateUtil : JsonConverter<DateTime>
{
    public override DateTime Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        if (reader.TokenType == JsonTokenType.Number)
        {
            // Lire le timestamp en millisecondes
            long timestamp = reader.GetInt64();
            // Convertir le timestamp en DateTime
            return DateTimeOffset.FromUnixTimeMilliseconds(timestamp).UtcDateTime;
        }
        throw new JsonException("Invalid timestamp format");
    }

    public override void Write(Utf8JsonWriter writer, DateTime value, JsonSerializerOptions options)
    {
        // Convertir DateTime en timestamp en millisecondes
        long timestamp = new DateTimeOffset(value).ToUnixTimeMilliseconds();
        writer.WriteNumberValue(timestamp);
    }
    public static DateTime convertDate(long timestamp)
    {
        return DateTimeOffset.FromUnixTimeMilliseconds(timestamp).UtcDateTime;
    }
    
}