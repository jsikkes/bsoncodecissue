package com.sikkes.bsoncodecissue.codecs;

import com.sikkes.bsoncodecissue.models.FormulaId;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class FormulaIdCodec implements Codec<FormulaId> {
    @Override
    public FormulaId decode(BsonReader reader, DecoderContext decoderContext) {
        return new FormulaId(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, FormulaId value, EncoderContext encoderContext) {
        if (value != null) {
            writer.writeString(value.getValue());
        } else {
            writer.writeNull();
        }
    }

    @Override
    public Class<FormulaId> getEncoderClass() {
        return FormulaId.class;
    }
}
