/**
 * 
 */
package script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Drew
 * @author Dr. Roberts
 */
public class ScriptHandler {

	private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
	
	private static Invocable invoc = (Invocable) engine;

	/**
	 * Used to bind the provided object to the name in the scope of the scripts
	 * being executed by this engine.
	 */
	public static void bindArg(String name, Object obj) {
		engine.put(name,obj);
	}
	
	/**
	 * Will load the script source from the provided filename.
	 */
	public static void loadScript(String script_name) {
		try {
			engine.eval(new java.io.FileReader(script_name));
		}
		catch(ScriptException se) {
			se.printStackTrace();
		}
		catch(java.io.IOException iox) {
			iox.printStackTrace();
		}
	}

	/**
	 * Will invoke the "update" function of the script loaded by this engine
	 * without any parameters.
	 */
	public static void executeScript() {
		try {
			invoc.invokeFunction("update");
		}
		catch(ScriptException se) {
			se.printStackTrace();
		}
		catch(NoSuchMethodException nsme) {
			nsme.printStackTrace();
		}
	}

	/**
	 * Will invoke the "update" function of the script loaded by this engine
	 * with the provided list of parameters.
	 */
	public static void executeScript(Object... args) {
		try {
			invoc.invokeFunction("update",args);
		}
		catch(ScriptException se) {
			se.printStackTrace();
		}
		catch(NoSuchMethodException nsme) {
			nsme.printStackTrace();
		}
	}

}
