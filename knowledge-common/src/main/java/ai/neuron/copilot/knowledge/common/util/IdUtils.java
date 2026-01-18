package ai.neuron.copilot.knowledge.common.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import java.util.UUID;

public final class IdUtils {

	private static final NoArgGenerator UUID_GENERATOR = Generators.timeBasedEpochGenerator();

	public static UUID uuid() {
		return UUID_GENERATOR.generate();
	}

	public static String uuidString() {
		return uuid().toString();
	}

}