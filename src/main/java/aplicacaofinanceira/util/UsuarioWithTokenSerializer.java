package aplicacaofinanceira.util;

import aplicacaofinanceira.model.Usuario;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class UsuarioWithTokenSerializer extends StdSerializer<Usuario> {

    public UsuarioWithTokenSerializer() {
        this(null);
    }

    public UsuarioWithTokenSerializer(Class<Usuario> t) {
        super(t);
    }

    @Override
    public void serialize(Usuario value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeStringField(Constants.TOKEN, value.getToken());
        jgen.writeEndObject();
    }

    public static String serializeUsuarioWithToken(Usuario usuario) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Usuario.class, new UsuarioWithTokenSerializer());
        mapper.registerModule(module);

        return mapper.writeValueAsString(usuario);
    }
}
