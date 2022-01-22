package com.project.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Writer;
import java.nio.file.FileSystems;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * parse java file and get the method calls
 *
 */
public class App {
    public static List<HashMap<String, String>> valuesList = new ArrayList<HashMap<String, String>>();

    public static List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();


    // private static ArrayList<String> records = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> files_r = new ArrayList<String>();
        List<String> files = new ArrayList<String>();

        files = readFile();
        files_r = Arrays.asList("D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/stat/AbstractStoreUnivariate.java",      
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/stat/CertifiedDataTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/ContinuedFraction.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/special/Gamma.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/Freq.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/MathUtils.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/stat/ListUnivariateImplTest.java",       
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/stat/StoreUnivariateImplTest.java",      
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/stat/UnivariateImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/stat/UnivariateImplTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/MathUtilsTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/distribution/AbstractContinuousDistribution.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/distribution/ExponentialDistributionImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/RandomDataImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/RandomDataTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/distribution/ChiSquaredDistributionImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/distribution/GammaDistributionImpl.java","D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/stat/BivariateRegression.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/distribution/ExponentialDistributionTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/stat/ListUnivariateImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/ValueServerTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/RealMatrixImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/stat/StatUtils.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/distribution/TDistributionImpl.java",    
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/EmpiricalDistributionImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/stat/StoreUnivariateImpl.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/FixedDoubleArray.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/special/BetaTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/ExpandableDoubleArray.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/FixedDoubleArrayTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/special/GammaTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/distribution/DistributionFactoryImplTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/ValueServer.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/ExpandableDoubleArrayTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/ContractableDoubleArrayTest.java",       
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/special/Beta.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/ContractableDoubleArray.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/EmpiricalDistributionTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/FreqTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/RealMatrixImplTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/stat/BeanListUnivariateImplTest.java",   
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/stat/BivariateRegressionTest.java",      
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/stat/StatUtilsTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/TestStatisticTest.java",
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/distribution/FDistributionImpl.java",    
"D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/java/org/apache/commons/math/TestStatisticImpl.java");
        //          "D:/detect/projects/111_e3ea7f7638a18820580408e7a412ec78c33d0144/src/test/org/apache/commons/math/FreqTest.java");

        for (int i = 1; i< files.size(); i++) {
            // System.out.println("file: " + files.get(i));
            String file_ins = files.get(i);

            // System.out.println("-----------------------------------------------------");
            fetchInfo(file_ins);

            // break;
        }

       
        final File filew = new File("parse_java.csv");
        // Create a File and append if it already exists.
        final Writer writer = new FileWriter(filew, true);
        // Copy List of Map Object into CSV format at specified File location.
        csvWriter(valuesList, writer);
    }

    private static void fetchInfo(String file_ins) {
        ArrayList<Integer> endlines = new ArrayList<Integer>();

        try {
            // System.out.println("file: " + file_ins);
            File file = new File(file_ins);

            CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(file));

            Boolean foundinner = false;
            for (TypeDeclaration typeDec : cu.getTypes()) {
                List<BodyDeclaration> members = typeDec.getMembers();
                if (members != null) {
                    for (BodyDeclaration member : members) {
                        if (member.isClassOrInterfaceDeclaration()) {
                            ClassOrInterfaceDeclaration cl = (ClassOrInterfaceDeclaration) member;
                            List<BodyDeclaration<?>> members2 = cl.getMembers();

                            if (members2 != null) {
                                for (BodyDeclaration member2 : members2) {
                                    collectMethod(member2, endlines, file_ins);
                                }
                            }
                        }
                        collectMethod(member, endlines, file_ins);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 
        for (HashMap<String, String> map : valuesList) {
            int comment_start = find_comment_range(endlines, map.get("startline"));
            map.put("comment_start", String.valueOf(comment_start));
        }
    }

    private static int find_comment_range(ArrayList<Integer> endlines, String startline) {
        int comment_start = 0;
        Arrays.sort(endlines.toArray());
        endlines.sort(Integer::compareTo);
        for (int i = 0; i < endlines.size(); i++) {
            if (endlines.get(i) > Integer.parseInt(startline)) {
                if (i != 0) {
                    comment_start = endlines.get(i - 1);
                } else
                    comment_start = 0;
                break;
            }
        }
        return comment_start + 1;
    }

    private static void collectMethod(BodyDeclaration method, ArrayList<Integer> endlines, String file_ins) {
        HashMap<String, String> map = new HashMap<>();
         
        if (method.isMethodDeclaration() || method.isConstructorDeclaration()) {
            file_ins = file_ins.replace("C:/cygwin64/home/NiCad-6.2/systems/", "");
            map.put("file", file_ins);
            if (method.isMethodDeclaration()) {
                MethodDeclaration field = (MethodDeclaration) method;   
                int startline = field.getBegin().get().line;
                int endline = field.getEnd().get().line;

                Optional<Comment> commentOptional = field.getComment();
                map_comment_block(commentOptional, map);
                
                Optional<BlockStmt> body = field.getBody();
                if (body.isPresent()) {
                    BlockStmt block = body.get();
                    map_body_block(block, map, startline);                    
                } else {
                    map.put("body", "");
                    map.put("comment_inner_satd", "");
                }
                endlines.add(endline);
                String methodName = field.getNameAsString();
                map.put("startline", Integer.toString(startline));
                map.put("endline", Integer.toString(endline));
                map.put("method", methodName);
                valuesList.add(map);    
            } 
            else {
                ConstructorDeclaration field = (ConstructorDeclaration) method;
                int startline = field.getBegin().get().line;
                int endline = field.getEnd().get().line;

                Optional<Comment> commentOptional = field.getComment();           
                map_comment_block(commentOptional, map);
                BlockStmt body = field.getBody();
                map_body_block(body, map, startline);
                
                endlines.add(endline);
                String methodName = field.getNameAsString();
                map.put("startline", Integer.toString(startline));
                map.put("endline", Integer.toString(endline));
                map.put("method", methodName);
                valuesList.add(map);    
            }                
        }
    }

    /**
     * 
     * @param commentOptional
     * @param map
     */
    private static void map_comment_block(Optional<Comment> commentOptional, HashMap<String, String> map) {
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            String comment_text = comment.getContent();
            int comment_outer_start = comment.getBegin().get().line;

            String[] comment_text_split = comment_text.split("\n");
            String comment_text_final = "";
            String comment_outer_satd = "";
            for (int i = 0; i < comment_text_split.length; i++) {
                if (i != 0) {
                    comment_text_final += "\n";
                }
                comment_text_final += comment_text_split[i];

                String text = comment_text_split[i];

                if (text != null && !text.equals("")) {
                    String text_lower = text.toLowerCase();

                    if (text_lower.contains("todo") || text_lower.contains("fixme") || text_lower.contains("xxx")) {
                        int comment_line = i + comment_outer_start;
                        comment_outer_satd += comment_line + "_" + text + "\n";                       
                    }
                }                    
            }
            map.put("comment", comment_text_final);
            map.put("comment_outer_satd", comment_outer_satd);
            map.put("comment_outer_start", Integer.toString(comment_outer_start));
        } else {
            map.put("comment", "");
            map.put("comment_outer_satd", "");
            map.put("comment_outer_start", "");
        }
        
    }

    /**
     * 
     */
    private static void map_body_block(BlockStmt block, HashMap<String, String> map, int startline) {
        String body_text = block.toString();
        String[] body_text_split = body_text.split("\n");
        String body_text_final = "";
        String comment_inner_satd = "";
        for (int i = 0; i < body_text_split.length; i++) {
            if (i != 0) {
                body_text_final += "\n";
            }
            body_text_final += body_text_split[i];

            String text = body_text_split[i];
            if (text != null && !text.equals("")) {
                text = text.toLowerCase();

                if (text.contains("todo") || text.contains("fixme") || text.contains("xxx")) {
                    int comment_line = i + startline;
                    comment_inner_satd += comment_line + "_" + body_text_split[i] + "\n";
                }
            }    
            
        }
        map.put("body", body_text_final);
        map.put("comment_inner_satd", comment_inner_satd);

    }

    /**
     * @param listOfMap
     * @param writer
     * @throws IOException
     */
    private static void csvWriter(final List<HashMap<String, String>> listOfMap, final Writer writer)
            throws IOException {
        CsvSchema schema = null;
        final CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        if (listOfMap != null && !listOfMap.isEmpty()) {
            for (final String col : listOfMap.get(0).keySet()) {
                schemaBuilder.addColumn(col);
            }
            schema = schemaBuilder.build().withLineSeparator(System.lineSeparator()).withHeader();
        }
        final CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).writeAll(listOfMap);
        writer.flush();
    }

    /**
     * 
     */
    private static ArrayList<String> readFile() throws FileNotFoundException, IOException {

        ArrayList<String> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader("C:/UNI/projects/satd_analyze/output.csv"));
       
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String value = values[2]; // check the index in the file and change accordingly

                value = value.replace('\\', '/');
                // value = value.replace("D:/detect/projects", "");
                value = value.replace("\"", "");
                value = "C:/cygwin64/home/NiCad-6.2/systems/" + value;

                // value = value.replace("\\", "\\\\");

                if (records.contains(value)) {
                    continue;
                } else {
                    records.add(value);
                }
            }
        } finally {
            reader.close();
        }

        return records;
    }
}

/*
 * <go to your project directory>
 * 
 * mvn clean compile assembly:single
 * 
 * java -cp target/code-parse-1.0-SNAPSHOT-jar-with-dependencies.jar com.project.parse.App
 * 
 */