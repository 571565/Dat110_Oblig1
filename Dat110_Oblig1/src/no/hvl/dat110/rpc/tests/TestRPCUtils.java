package no.hvl.dat110.rpc.tests;


import no.hvl.dat110.rpc.RPCUtils;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRPCUtils {

	@Test
	public void testMarshallString() {
		
		String str = "teststring";
		
		byte[] encoded = RPCUtils.marshallString((byte)0, str);
		String decoded = RPCUtils.unmarshallString(encoded);
		
		assertEquals(str,decoded);
	}
	
	@Test
	public void testMarshallInteger() {
		
		int testint = 255;
		
		byte[] encoded = RPCUtils.marshallInteger((byte)0, testint);
		int decoded = RPCUtils.unmarshallInteger(encoded);
		
		assertEquals(testint,decoded);
	}
	
	@Test
	public void testMarshallBoolean( ) {
		
		byte[] encoded = RPCUtils.marshallBoolean((byte)0, true);
		boolean decoded = RPCUtils.unmarshallBoolean(encoded);
		
		assertTrue(decoded);
		
		encoded = RPCUtils.marshallBoolean((byte)0, false);
		decoded = RPCUtils.unmarshallBoolean(encoded);
		
		assertFalse(decoded);
		
	}
}
