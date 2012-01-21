package me.menexia.guardianscrolls;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class GSDataHandler {
	public static GuardianScrolls gs;
	public GSDataHandler(GuardianScrolls instance) {
		gs = instance;
	}
	
	/**
	 * Saves the object in a file.
	 * @param obj Object to save
	 * @param path Path to save to
	 * @throws Exception
	 */
	public static void save(Object obj, String path) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}
	
	/**
	 * Loads object from a file
	 * @param path Path to load from
	 * @return Object from file ready to be used
	 * @throws Exception
	 */
	public static Object load(String path) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return result;
		}
	
}
