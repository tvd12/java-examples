package com.tvd12.example.pattern.jackson;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;

public class JacksonCoreTest {

    public static void main(String[] args) throws Exception {
        ObjectCodec codec = new ObjectCodec() {

            @Override
            public void writeValue(JsonGenerator gen, Object value) throws IOException {
                // TODO Auto-generated method stub
            }

            @Override
            public void writeTree(JsonGenerator gen, TreeNode tree) throws IOException {
                // TODO Auto-generated method stub

            }

            @Override
            public Version version() {
                return PackageVersion.VERSION;
            }

            @Override
            public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public JsonParser treeAsTokens(TreeNode n) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <T> Iterator<T> readValues(JsonParser p, ResolvedType valueType) throws IOException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <T> Iterator<T> readValues(JsonParser p, Class<T> valueType) throws IOException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <T> Iterator<T> readValues(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
                return null;
            }

            @Override
            public <T> T readValue(JsonParser p, ResolvedType valueType) throws IOException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <T> T readValue(JsonParser p, Class<T> valueType) throws IOException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <T> T readValue(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
                return null;
            }

            @Override
            public <T extends TreeNode> T readTree(JsonParser p) throws IOException {
                return null;
            }

            @Override
            public TreeNode createObjectNode() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public TreeNode createArrayNode() {
                // TODO Auto-generated method stub
                return null;
            }
        };
        String json = "{\"enable\": true}";
        JsonFactory factory = new JsonFactory(codec);
        JsonParser parser = factory.createParser(json);
        TreeNode treeNode = parser.readValueAsTree();
        System.out.println(treeNode);
    }

}
