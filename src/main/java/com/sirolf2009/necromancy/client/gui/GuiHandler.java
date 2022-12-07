package com.sirolf2009.necromancy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.sirolf2009.necromancy.block.BlockAltar;
import com.sirolf2009.necromancy.block.BlockSewing;
import com.sirolf2009.necromancy.inventory.ContainerAltar;
import com.sirolf2009.necromancy.inventory.ContainerSewing;
import com.sirolf2009.necromancy.tileentity.TileEntityAltar;
import com.sirolf2009.necromancy.tileentity.TileEntitySewing;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == BlockAltar.guiID)
            return new ContainerAltar(player.inventory, (TileEntityAltar) player.worldObj.getTileEntity(new BlockPos(x,y,z)));
        if (ID == BlockSewing.guiID)
            return new ContainerSewing(player.inventory, player.worldObj.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == BlockAltar.guiID)
            return new GuiAltar(player.inventory, (TileEntityAltar) player.worldObj.getTileEntity(new BlockPos(x,y,z)));
        if (ID == BlockSewing.guiID)
            return new GuiSewing(player.inventory, (TileEntitySewing) player.worldObj.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

}