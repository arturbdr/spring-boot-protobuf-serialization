package com.benchmark.jackson;

import com.dslplatform.json.CompiledJson;
import com.jsoniter.output.JsonStream;

@CompiledJson
public class TestObject {

    public int field1;
    public int field2;
    public int field3;
    public int field4;
    public int field5;
    public int field6;
    public int field7;
    public int field8;
    public int field9;
    public int field10;

    public static TestObject createTestObject() {
        TestObject testObject = new TestObject();
        testObject.field1 = 31415926;
        testObject.field2 = 61415923;
        testObject.field3 = 31415269;
        testObject.field4 = 53141926;
        testObject.field5 = 13145926;
        testObject.field6 = 43115926;
        testObject.field7 = 31419265;
        testObject.field8 = 23141596;
        testObject.field9 = 43161592;
        testObject.field10 = 112;
        return testObject;
    }

    public static byte[] createTestJSON() {
        return JsonStream.serialize(createTestObject()).getBytes();
    }

}
