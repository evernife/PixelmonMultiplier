package br.com.finalcraft.pixelmonmultiplier;

import br.com.finalcraft.pixelmonmultiplier.commands.CommandRegisterer;
import br.com.finalcraft.pixelmonmultiplier.config.ConfigManager;
import br.com.finalcraft.pixelmonmultiplier.listeners.PixelmonForgeListener;
import com.google.inject.Inject;
import com.pixelmonmod.pixelmon.Pixelmon;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = "pixelmonmultiplier",
		name = "EverNife PixelmonMultiplier",
		version = "1.0.1a",
		description = "PixelmonMultiplier For FinalCraft Network",
		dependencies = {
					@Dependency(id = "evernifecorespongy"),
					@Dependency(id = "pixelmon")
				})
public class PixelmonMultiplier {

	public static PluginContainer instance;

	public static Logger getPluginLogger(){
		return instance.getLogger();
	}

	@Inject
	private Logger logger;
	public Logger getLogger(){
		return logger;
	}

	@Listener
	public void init(GameInitializationEvent event) {
		Pixelmon.EVENT_BUS.register(new PixelmonForgeListener());
	}

	@Listener
	public void onServerStart(GameStartedServerEvent event) {
		instance = Sponge.getPluginManager().fromInstance(this).get();

		logger.info("Carregando configurações...");
		ConfigManager.initialize(instance);

		logger.info("Registrando comandos...");
		CommandRegisterer.registerCommands(instance);

		logger.info("Plugin iniciado com sucesso!");
	}
}
