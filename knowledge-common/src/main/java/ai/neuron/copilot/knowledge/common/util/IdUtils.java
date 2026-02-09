package ai.neuron.copilot.knowledge.common.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import java.util.UUID;

public final class IdUtils {

	private static final NoArgGenerator UUID_V7_GENERATOR = Generators.timeBasedEpochGenerator();

	public static UUID uuidV4() {
		return UUID.randomUUID();
	}

	public static String uuidV4Str() {
		return UUID.randomUUID().toString();
	}

	public static String trimmedUuidV4() {
		return uuidV4Str().replaceAll("-", "");
	}


	public static UUID uuidV7() {
		return UUID_V7_GENERATOR.generate();
	}

	public static String uuidV7Str() {
		return uuidV7().toString();
	}

	public static String trimmedUuidV7() {
		return uuidV7Str().replaceAll("-", "");
	}

}