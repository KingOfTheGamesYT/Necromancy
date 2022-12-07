package com.sirolf2009.necromancy.entity;

import java.awt.Color;

import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.Necromancy;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityCaveSpider;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityChicken;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityCow;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityCreeper;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityEnderman;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityIronGolem;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityIsaac;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityPig;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityPigZombie;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySheep;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySkeleton;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySpider;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntitySquid;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityVillager;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityWitch;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityWolf;
import com.sirolf2009.necromancy.entity.necroapi.NecroEntityZombie;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class RegistryNecromancyEntities
{

    public static int entityIDTeddy;
    public static int entityIDIsaac;
    public static int villagerIDNecro;
    
    public static void initEntities()
    {
        entityIDTeddy = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityTeddy.class, "teddyNecro", entityIDTeddy, new Color(99, 69, 29).getRGB(), Color.red.getRGB());
        EntityRegistry.registerModEntity(EntityTeddy.class, "teddyNecro", 0, Necromancy.instance, 25, 5, true);

        EntityRegistry.registerGlobalEntityID(EntityNightCrawler.class, "NightCrawler", EntityRegistry.findGlobalUniqueEntityId(),
                new Color(6, 6, 6).getRGB(), new Color(13, 13, 13).getRGB());
        EntityRegistry.registerModEntity(EntityNightCrawler.class, "NightCrawler", 1, Necromancy.instance, 25, 5, true);

        entityIDIsaac = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityIsaacNormal.class, "IsaacNormal", entityIDIsaac, new Color(6, 6, 6).getRGB(), new Color(204, 153,
                153).getRGB());
        EntityRegistry.registerModEntity(EntityIsaacNormal.class, "IsaacNormal", 2, Necromancy.instance, 25, 5, true);

        EntityRegistry.registerGlobalEntityID(EntityIsaacBlood.class, "IsaacBlood", EntityRegistry.findGlobalUniqueEntityId(),
                new Color(16, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        EntityRegistry.registerModEntity(EntityIsaacBlood.class, "IsaacBlood", 3, Necromancy.instance, 25, 5, true);

        EntityRegistry.registerGlobalEntityID(EntityIsaacHead.class, "IsaacHead", EntityRegistry.findGlobalUniqueEntityId(),
                new Color(26, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        EntityRegistry.registerModEntity(EntityIsaacHead.class, "IsaacHead", 4, Necromancy.instance, 25, 5, true);

        EntityRegistry.registerGlobalEntityID(EntityIsaacBody.class, "IsaacBody", EntityRegistry.findGlobalUniqueEntityId(),
                new Color(36, 6, 6).getRGB(), new Color(214, 153, 153).getRGB());
        EntityRegistry.registerModEntity(EntityIsaacBody.class, "IsaacBody", 5, Necromancy.instance, 25, 5, true);

        EntityRegistry.registerGlobalEntityID(EntityMinion.class, "minionNecro", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityMinion.class, "minionNecro", 6, Necromancy.instance, 25, 5, true);

        EntityRegistry.registerModEntity(EntityTear.class, "TearNormal", 7, Necromancy.instance, 32, 5, true);
        EntityRegistry.registerModEntity(EntityTearBlood.class, "TearBlood", 8, Necromancy.instance, 32, 5, true);

        for (int freeID = 0; freeID < 100; freeID++)
        {
            if (!VillagerRegistry.getRegisteredVillagers().contains(Integer.valueOf(freeID)))
            {
                villagerIDNecro = freeID;
                VillagerRegistry.instance().registerVillagerId(villagerIDNecro);
                VillagerRegistry.instance().registerVillageTradeHandler(RegistryNecromancyEntities.villagerIDNecro, Necromancy.villageHandler);
                break;
            }
        }

        NecroEntityRegistry.registerEntity(new NecroEntityCaveSpider());
        NecroEntityRegistry.registerEntity(new NecroEntityChicken());
        NecroEntityRegistry.registerEntity(new NecroEntityCow());
        NecroEntityRegistry.registerEntity(new NecroEntityCreeper());
        NecroEntityRegistry.registerEntity(new NecroEntityEnderman());
        NecroEntityRegistry.registerEntity(new NecroEntityIronGolem());
        NecroEntityRegistry.registerEntity(new NecroEntityIsaac());
        NecroEntityRegistry.registerEntity(new NecroEntityPig());
        NecroEntityRegistry.registerEntity(new NecroEntityPigZombie());
        NecroEntityRegistry.registerEntity(new NecroEntitySheep());
        NecroEntityRegistry.registerEntity(new NecroEntitySkeleton());
        NecroEntityRegistry.registerEntity(new NecroEntitySpider());
        NecroEntityRegistry.registerEntity(new NecroEntitySquid());
        NecroEntityRegistry.registerEntity(new NecroEntityVillager());
        NecroEntityRegistry.registerEntity(new NecroEntityWolf());
        NecroEntityRegistry.registerEntity(new NecroEntityWitch());
        NecroEntityRegistry.registerEntity(new NecroEntityZombie());
    }
}
