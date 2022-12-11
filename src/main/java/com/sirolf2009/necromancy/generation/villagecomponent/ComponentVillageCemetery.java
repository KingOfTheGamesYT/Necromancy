package com.sirolf2009.necromancy.generation.villagecomponent;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import com.sirolf2009.necroapi.NecroEntityBase;
import com.sirolf2009.necroapi.NecroEntityRegistry;
import com.sirolf2009.necromancy.entity.RegistryNecromancyEntities;

public class ComponentVillageCemetery extends StructureVillagePieces.Village
{

    private int averageGroundLevel = -1;
    
    public ComponentVillageCemetery() {}
    
    public ComponentVillageCemetery(StructureVillagePieces.Start par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, EnumFacing par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        coordBaseMode = par5;
        boundingBox = par4StructureBoundingBox;
    }

    @SuppressWarnings("rawtypes")
    public static ComponentVillageCemetery build(StructureVillagePieces.Start startPiece, List pieces, Random random, int par3, int par4, int par5, EnumFacing par6, int par7)
    {
        StructureBoundingBox sbox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 17, 5, 18, par6);
        return canVillageGoDeeper(sbox) && StructureComponent.findIntersecting(pieces, sbox) == null ? new ComponentVillageCemetery(startPiece, par7,
                random, sbox, par6) : null;
    }

    @Override
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        if (averageGroundLevel < 0)
        {
            averageGroundLevel = this.getAverageGroundLevel(par1World, par3StructureBoundingBox);
            if (averageGroundLevel < 0)
                return true;
        }
        boundingBox.offset(0, averageGroundLevel - boundingBox.minY - 1, 0);

        System.out.println("Necromod generating structure, at: " + boundingBox.getCenter());

        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 17, 5, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false); // clear
        // area
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 17, 0, 18, Blocks.grass.getDefaultState(), Blocks.grass.getDefaultState(), false); // create
                                                                                                                    // ground
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 17, 1, 0, Blocks.cobblestone_wall.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // front
                                                                                                                                    // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 17, 16, 1, 18, Blocks.cobblestone_wall.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // back
                                                                                                                                      // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 1, 17, Blocks.cobblestone_wall.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // left
                                                                                                                                    // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 17, 1, 0, 17, 1, 18, Blocks.cobblestone_wall.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // right
                                                                                                                                      // wall
        for (int i = 0; i < 4; i++)
        { // left graves
            setBlockState(par1World, Blocks.cobblestone.getDefaultState(), 3, 1, 2 + i * 2,  par3StructureBoundingBox);
            if (par2Random.nextInt(10) == 1)
            {
                generateBodypartChest(par1World, par3StructureBoundingBox, par2Random, 3, 0, 2 + i * 2);
            }
            setBlockState(par1World, Blocks.soul_sand.getDefaultState(), 4, 0, 2 + i * 2,  par3StructureBoundingBox);
            setBlockState(par1World, Blocks.soul_sand.getDefaultState(), 5, 0, 2 + i * 2,  par3StructureBoundingBox);
        }
        for (int i = 0; i < 4; i++)
        { // right graves
            setBlockState(par1World, Blocks.soul_sand.getDefaultState(), 11, 0, 2 + i * 2, par3StructureBoundingBox);
            setBlockState(par1World, Blocks.soul_sand.getDefaultState(), 12, 0, 2 + i * 2,  par3StructureBoundingBox);
            setBlockState(par1World, Blocks.cobblestone.getDefaultState(), 13, 1, 2 + i * 2,  par3StructureBoundingBox);
            if (par2Random.nextInt(10) == 1)
            {
                generateBodypartChest(par1World, par3StructureBoundingBox, par2Random, 13, 0, 2 + i * 2);
            }
        }
        fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 0, 10, 1, 0, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false); // clear
        // door
        // area
        fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 0, 5, 3, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // door
                                                                                                                              // part
        fillWithBlocks(par1World, par3StructureBoundingBox, 6, 3, 0, 6, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // door
                                                                                                                              // part
        fillWithBlocks(par1World, par3StructureBoundingBox, 7, 4, 0, 7, 5, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // door
                                                                                                                              // part
        setBlockState(par1World, Blocks.cobblestone.getDefaultState(), 8, 5, 0,  par3StructureBoundingBox); // door
                                                                                                          // part
        fillWithBlocks(par1World, par3StructureBoundingBox, 9, 4, 0, 9, 5, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // door
                                                                                                                              // part
        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 3, 0, 10, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // door
                                                                                                                                // part
        fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 0, 11, 3, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // door
                                                                                                                                // part

        fillWithBlocks(par1World, par3StructureBoundingBox, 7, 0, 0, 9, 0, 14, Blocks.gravel.getDefaultState(), Blocks.gravel.getDefaultState(), false); // path

        fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 11, 5, 3, 11, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // tomb
                                                                                                                                // front
                                                                                                                                // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 15, 5, 3, 15, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // tomb
                                                                                                                                // back
                                                                                                                                // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 12, 2, 3, 14, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // tomb
                                                                                                                                // left
                                                                                                                                // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 12, 6, 3, 14, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // tomb
                                                                                                                                // right
                                                                                                                                // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 12, 5, 0, 14, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // tomb
                                                                                                                                // floor
        fillWithBlocks(par1World, par3StructureBoundingBox, 3, 4, 12, 5, 4, 14, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // tomb
                                                                                                                                // roof
        setBlockState(par1World, Blocks.cobblestone.getDefaultState(), 3, 1, 13, par3StructureBoundingBox); // tomb
                                                                                                           // chest
                                                                                                           // seperator
        for (int i = 0; i < 3; i++)
        {
            setBlockState(par1World, Blocks.stone_brick_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_brick_stairs, 3)), 3 + i, 4, 11,
                    par3StructureBoundingBox); // tomb
                                               // front
                                               // stair
                                               // roof
        }
        for (int i = 0; i < 3; i++)
        {
            setBlockState(par1World, Blocks.stone_brick_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_brick_stairs, 2)), 3 + i, 4, 15,
                    par3StructureBoundingBox); // tomb
                                               // back
                                               // stair
                                               // roof
        }
        for (int i = 0; i < 3; i++)
        {
            setBlockState(par1World, Blocks.stone_brick_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_brick_stairs, 0)), 2, 4, 12 + i,
                    par3StructureBoundingBox); // tomb
                                               // left
                                               // stair
                                               // roof
        }
        for (int i = 0; i < 3; i++)
        {
            setBlockState(par1World, Blocks.stone_brick_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_brick_stairs, 1)), 6, 4, 12 + i,
                    par3StructureBoundingBox); // tomb
                                               // right
                                               // stair
                                               // roof
        }
        setBlockState(par1World, Blocks.cobblestone.getDefaultState(), 6, 0, 13, par3StructureBoundingBox); // tomb
                                                                                                           // door
                                                                                                           // support
        placeDoorCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 6, 1, 13, EnumFacing.getHorizontal(getMetadataWithOffset(Blocks.oak_door, 0)));

        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 0, 11, 14, 0, 15, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // house
                                                                                                                                  // floor
        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 11, 14, 3, 11, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false); // house
                                                                                                                        // front
                                                                                                                        // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 15, 14, 3, 15, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false); // house
                                                                                                                        // back
                                                                                                                        // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 11, 10, 3, 15, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false); // house
                                                                                                                        // left
                                                                                                                        // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 14, 1, 11, 14, 3, 15, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false); // house
                                                                                                                        // right
                                                                                                                        // wall
        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 4, 11, 14, 4, 15, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false); // house
                                                                                                                  // roof
        fillWithBlocks(par1World, par3StructureBoundingBox, 11, 4, 12, 13, 4, 14, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false); // house
                                                                                                                        // roof
        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 11, 10, 3, 11, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // house
                                                                                                                                  // front
                                                                                                                                  // left
                                                                                                                                  // corner
        fillWithBlocks(par1World, par3StructureBoundingBox, 14, 1, 11, 14, 3, 11, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // house
                                                                                                                                  // front
                                                                                                                                  // right
                                                                                                                                  // corner
        fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 15, 10, 3, 15, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // house
                                                                                                                                  // back
                                                                                                                                  // left
                                                                                                                                  // corner
        fillWithBlocks(par1World, par3StructureBoundingBox, 14, 1, 15, 14, 3, 15, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false); // house
                                                                                                                                  // back
                                                                                                                                  // right
                                                                                                                                  // corner
        placeDoorCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 10, 1, 13, EnumFacing.fromAngle(getMetadataWithOffset(Blocks.oak_door, 2)));

        generateBodypartChest(par1World, par3StructureBoundingBox, par2Random, 3, 1, 12);
        generateBodypartChest(par1World, par3StructureBoundingBox, par2Random, 3, 1, 14);
        this.spawnVillagers(par1World, par3StructureBoundingBox, 11, 3, 12, 1);
        return true;
    }

    private void generateBodypartChest(World par1World, StructureBoundingBox structureBoundingBox, Random par2Random, int x, int y, int z, Object... content)
    {
        int mobs = NecroEntityRegistry.registeredEntities.size();
        ItemStack headItem, torsoItem, armLeftItem, armRightItem, legItem;
        while (true)
        {
            headItem = ((NecroEntityBase) NecroEntityRegistry.registeredEntities.values().toArray()[par2Random.nextInt(mobs)]).headItem;
            torsoItem = ((NecroEntityBase) NecroEntityRegistry.registeredEntities.values().toArray()[par2Random.nextInt(mobs)]).torsoItem;
            armLeftItem = ((NecroEntityBase) NecroEntityRegistry.registeredEntities.values().toArray()[par2Random.nextInt(mobs)]).armItem;
            armRightItem = ((NecroEntityBase) NecroEntityRegistry.registeredEntities.values().toArray()[par2Random.nextInt(mobs)]).armItem;
            legItem = ((NecroEntityBase) NecroEntityRegistry.registeredEntities.values().toArray()[par2Random.nextInt(mobs)]).legItem;
            if (headItem != null && torsoItem != null && armLeftItem != null && armRightItem != null && legItem != null)
            {
                break;
            }
        }
        generateChest(par1World, structureBoundingBox, x, y, z, 12, headItem, 13, torsoItem, 14, legItem, 4, armRightItem, 22, armLeftItem);
    }

    private void generateChest(World par1World, StructureBoundingBox structureBoundingBox, int x, int y, int z, Object... content)
    {
        BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

        if (structureBoundingBox.isVecInside(blockpos) && par1World.getBlockState(blockpos).getBlock() != Blocks.chest)
        {
            IBlockState iblockstate = Blocks.chest.getDefaultState();
            par1World.setBlockState(blockpos, Blocks.chest.correctFacing(par1World, blockpos, iblockstate), 2);
            TileEntityChest tileentitychest = (TileEntityChest) par1World.getTileEntity(blockpos);

            if (tileentitychest != null)
            {
                for (int i = 0; i < content.length; i++)
                {
                    if (content[i] instanceof Integer && content[i + 1] instanceof ItemStack)
                    {
                        tileentitychest.setInventorySlotContents(Integer.valueOf(content[i].toString()), (ItemStack) content[i + 1]);
                    }
                }
            }
        }/*
          * if(par1World.getBlock(x, y, z) != Blocks.chest &&
          * par1World.getTileEntity(x, y, z) == null) {
          * placeBlockAtCurrentPosition(par1World, Blocks.chest, 0, x, y, z,
          * structureBoundingBox); int j1 =
          * this.getBiomeSpecificBlock(Blocks.chest, 0); int k1 =
          * this.getBiomeSpecificBlockMetadata(Blocks.chest, 0); int x1 =
          * this.getXWithOffset(j1, k1); int y1 = this.getYWithOffset(y); int z1
          * = this.getZWithOffset(j1, k1); TileEntityChest chest = null;
          * System.out.println(x1+", "+y1+", "+z1); chest = (TileEntityChest)
          * par1World.getTileEntity(x1, y1, z1); for(int i=0; i<content.length;
          * i++) { if(content[i] instanceof Integer && content[i+1] instanceof
          * ItemStack && chest != null) { System.out.println(chest);
          * System.out.println(Integer.valueOf(content
          * [i].toString())+" "+(ItemStack) content[i+1]);
          * chest.setInventorySlotContents
          * (Integer.valueOf(content[i].toString()), (ItemStack) content[i+1]);
          * } else if(chest != null) { throw new
          * IllegalArgumentException("Wrong chest content pars used: "
          * +content[i]+" "+content[i+1]); } i+=1; } }
          */
    }


    public int getVillagerType(int par1)
    {
        return RegistryNecromancyEntities.villagerIDNecro;
    }
}
