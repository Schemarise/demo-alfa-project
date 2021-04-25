package com.acme.model.rest;

import alfa.rt.AlfaObject;
import alfa.rt.DataConsumer;
import alfa.rt.DataSupplier;
import alfa.rt.codec.json.JsonCodecConfig;
import alfa.rt_int.IntImpl;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
// import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

// Take over the serialisation of Spring JSON to use ALFA serialisers
@Configuration
public class AlfaSpringJacksonConfig {
    @Bean
    public ObjectMapper jsonObjectMapper() {
        ArrayList<com.fasterxml.jackson.databind.Module> modules = new ArrayList<>();

        //CollectionType Serialization
        SimpleModule alfaJacksonModule = new SimpleModule();

        alfaJacksonModule.setSerializers(new AlfaSimpleSerializers());

        alfaJacksonModule.setDeserializers(new AlfaSimpleDeserializers());
        modules.add(alfaJacksonModule);
        // modules.add(new Jdk8Module());

        return Jackson2ObjectMapperBuilder.json()
                .modules(modules)
                .build();
    }

    private class AlfaSimpleDeserializers extends SimpleDeserializers {
        @Override
        public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
            if ( type.isTypeOrSubTypeOf( AlfaObject.class ) )
                return new AlfaJsonDeserializer(type);
            else
                return super.findBeanDeserializer(type, config, beanDesc);
        }
    }

    private class AlfaSimpleSerializers extends SimpleSerializers {
        private AlfaJsonSerializer alfaJsonSerializer = new AlfaJsonSerializer();

        @Override
        public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
            if ( type.isTypeOrSubTypeOf(AlfaObject.class ))
                return alfaJsonSerializer;
            else
                return super.findSerializer(config, type, beanDesc);
        }
    }

    private class AlfaJsonSerializer extends  JsonSerializer {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            AlfaObject ao = (AlfaObject) o;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try {
                DataConsumer dc = IntImpl.jsonDataConsumer(JsonCodecConfig.getInstance(), jsonGenerator, stream);
                dc.consume(ao);
            } catch ( Exception e ) {
                throw new IOException(e);
            }
        }
    }

    private class AlfaJsonDeserializer extends  JsonDeserializer {
        private final JavaType jtype;

        public AlfaJsonDeserializer(JavaType type) {
            jtype = type;
        }

        @Override
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            DataSupplier ds = IntImpl.jsonDataSupplier(JsonCodecConfig.getInstance(), jsonParser);
            try {
                AlfaObject obj = ds.objectValue(Optional.of(jtype.getRawClass()));
                return obj;
            } catch ( Exception e ) {
                throw new IOException(e);
            }
        }
    }
}