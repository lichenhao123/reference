package com.westos;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.util.*;

public class CreateTreasure {
    static String[] tips = {
            "你一锹挖下去，只有坚硬的岩石，震得双手隐隐作痛！",
            "你一锹挖下去，只有泥巴，你不禁叹息！",
            "你一锹挖下去，只有树根，卡住了你的铁锹！",
            "你一锹挖下去，黄鼠狼蹿了出来！",
            "你一锹挖下去，老鼠四处乱跑！",
            "你一锹挖下去，一只蛇蹿了出来，吓了你一跳！",
            "你一锹挖下去，铲倒了自己脚上，鲜血直流！"
    };

    static String[] success = {
            "你一锹挖下去，宝箱出现！里面有一个大大的红包，拆开一看【好好学习，天天向上】"
    };

    static String[] parameters = {
            "int",
            "double",
            "boolean",
            "char",
            "String"
    };

    static Random random = new Random();

    static String getRandomParameters() {
        int time = random.nextInt(parameters.length) + 1;
        StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < time; i++) {
            String p = parameters[random.nextInt(parameters.length)];
            sb.append(p).append(" ").append((char) (i + 97));
            if (i != time - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {

        int methodCount = 20;
        ArrayList<String> methods = new ArrayList<>(methodCount);
        for (int i = 0; i < methodCount; i++) {
            String methodName = "m" + UUID.randomUUID().toString().replace("-", "");
            StringBuilder method = new StringBuilder(200);
            if (i < methodCount - 1) {
                method.append("public void ").append(methodName).append("(").append(") {").append("\n");
                method.append("if(--count<0) throw new RuntimeException(\"体力耗尽，不能再挖了！\");\n");
                method.append("System.out.println(\"").append(tips[random.nextInt(tips.length)]).append("\");").append("\n");
            } else {
                method.append("@javax.annotation.Resource public void ").append(methodName).append("(").append(") {").append("\n");
                method.append("if(--count<0) throw new RuntimeException(\"体力耗尽，不能再挖了！\");\n");
                method.append("System.out.println(\"").append(success[0]).append("\");").append("\n");
            }
            method.append("}\n");
            methods.add(method.toString());
        }
        Collections.shuffle(methods);
        StringBuilder builder = new StringBuilder(methodCount * 200 + 100);
        builder.append("package com.westos;\n");
        builder.append("public class Treasure {\n");
        builder.append("static int count = 3;\n");
        builder.append("private Treasure() { System.out.println(\"忽然有光！\");}\n");
        for (String method : methods) {
            builder.append(method);
        }
        builder.append("}");
        compile(new StringBuilderJavaSource("com.westos.Treasure", builder));
    }

    public static void compile(StringBuilderJavaSource javaSource) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        List<ByteArrayJavaClass> list = new ArrayList<>();
        JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        fileManager = new ForwardingJavaFileManager<JavaFileManager>(fileManager) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
                ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
                list.add(fileObject);
                return fileObject;
            }
        };
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, Arrays.asList(javaSource));
        System.out.println(task.call());
        list.forEach(javaClass -> {
            try {
                new FileOutputStream("e:\\Treasure.class").write(javaClass.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    static class ByteArrayJavaClass extends SimpleJavaFileObject {
        private ByteArrayOutputStream out;

        protected ByteArrayJavaClass(String name) {
            super(URI.create("bytes:///" + name), Kind.CLASS);
            out = new ByteArrayOutputStream();
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return out;
        }

        public byte[] getBytes() {
            return out.toByteArray();
        }
    }

    static class StringBuilderJavaSource extends SimpleJavaFileObject {
        private StringBuilder code;

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }

        protected StringBuilderJavaSource(String name, StringBuilder code) {
            super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;

        }
    }

}
