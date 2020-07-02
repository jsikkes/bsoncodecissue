package com.sikkes.bsoncodecissue;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.sikkes.bsoncodecissue.codecs.FormulaIdCodec;
import com.sikkes.bsoncodecissue.codecs.ProductIdCodec;
import com.sikkes.bsoncodecissue.models.Formula;
import com.sikkes.bsoncodecissue.models.FormulaId;
import com.sikkes.bsoncodecissue.models.product.Product;
import com.sikkes.bsoncodecissue.models.product.ProductCategory;
import com.sikkes.bsoncodecissue.models.product.ProductId;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class App {
    private final static int MAX_THREADS = 8;
    private final static String DB_NAME = "test";

    private static CodecRegistry codecRegistry() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry customCodecRegistry = CodecRegistries.fromCodecs(
                new ProductIdCodec(),
                new FormulaIdCodec()
        );

        return CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), customCodecRegistry, pojoCodecRegistry);
    }

    private static Product newProduct(int id) {
        Product product = new Product(new ProductId(id));
        product.setName("Apple");
        product.setBrandName("Farmers");
        product.setVariantDescription("Jonagold");

        product.setCategories(new HashSet<>(Arrays.asList(
                new ProductCategory("01002003")
                        .withName("Apples per kilo")
                        .withFormula(new Formula(new FormulaId(1)))
                        .withFormula(new Formula(new FormulaId(2)))
                        .withParent(new ProductCategory("01002")
                                .withName("Fruit")
                                .withFormula(new Formula(new FormulaId(1)))
                                .withFormula(new Formula(new FormulaId(2)))
                                .withParent(new ProductCategory("01")
                                        .withName("Fruit & Vegetables")
                                        .withFormula(new Formula(new FormulaId(2)))

                                )
                        ),
                new ProductCategory("01002004")
                        .withName("Apples single")
                        .withFormula(new Formula(new FormulaId(1)))
                        .withFormula(new Formula(new FormulaId(2)))
                        .withParent(new ProductCategory("01002")
                                .withName("Fruit")
                                .withFormula(new Formula(new FormulaId(1)))
                                .withFormula(new Formula(new FormulaId(2)))
                                .withParent(new ProductCategory("01")
                                        .withName("Fruit & Vegetables")
                                        .withFormula(new Formula(new FormulaId(2)))
                                )
                        )
        )));

        return product;
    }


    private static void createOrUpdateProduct(MongoDatabase database, Product product) {
        try {
            final MongoCollection<Product> products = database.getCollection("products", Product.class);
            products.replaceOne(Filters.eq("_id", product.getProductId().getValue()), product, new ReplaceOptions().upsert(true));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        final MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
                .codecRegistry(codecRegistry()).build());
        final MongoDatabase database = mongoClient.getDatabase(DB_NAME);


        for (int i = 1; i < 2000; i++) {
            Product product = newProduct(i);

            // This succeeds
            // createOrUpdateProduct(database, product);


            // This fails
            executorService.submit(() -> App.createOrUpdateProduct(database, product));
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

}
