package llhusnire.menexia.guardianscrolls;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GSDataHandler {
	public static GuardianScrolls gs;
	public GSDataHandler(GuardianScrolls instance) {
		gs = instance;
	}
	
	public static void save(Object obj, String path) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}
	
	public static Object load(String path) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return result;
		}
	
	/*public Map<String, Map<Short, Integer>> scrollHM = gs.scrollHM;
	
	public static void saveScrollData() {
		// boolean worked = true; (?)
		gs.getDataFolder().mkdir();
		if ((!gs.scrollData.exists()) || (gs.scrollData == null)) {
			try {
				gs.scrollData.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				gs.logger.info("There was a problem creating scrolls data file, disabling plugin.");
				// worked = false;
			}
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(gs.scrollData));
				oos.writeObject(gs.scrollHM);
				oos.flush();
				oos.close();
			} catch (Exception e) {
				gs.logger.severe("There was a problem saving scroll data" + e.getMessage());
				// worked = false;
			}
		}
	}

	public static void loadScrollData() {
		// boolean worked = true;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(gs.scrollData));
			Object result = ois.readObject();
			gs.scrollHM = ((HashMap)result);
			ois.close();
		} catch (Exception e) {
			gs.logger.severe("There was a problem loading scroll data" + e.getMessage());
			// worked = false;
		}
	}*/
	
}
