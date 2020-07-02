package com.sikkes.bsoncodecissue.codecs;

import com.sikkes.bsoncodecissue.models.product.ProductId;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class ProductIdCodec implements Codec<ProductId> {
    @Override
    public ProductId decode(BsonReader reader, DecoderContext decoderContext) {
        return new ProductId(reader.readString());
    }

    @Override
    public void encode(BsonWriter writer, ProductId value, EncoderContext encoderContext) {
        if (value != null) {
            writer.writeString(value.getValue());
        } else {
            writer.writeNull();
        }
    }

    @Override
    public Class<ProductId> getEncoderClass() {
        return ProductId.class;
    }
}
