package com.sirolf2009.necromancy.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class CommandMinion extends CommandBase
{

    @Override
    public String getCommandName()
    {
        return "minion";
    }
    
    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "/minion [set [aggressive|passive]|friend <name>|enemy <name>]";
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2)
    {
        if (var2.length >= 2)
        {
            EntityPlayerMP player = getPlayer(var1, var1.getCommandSenderName());
            NBTTagCompound nbt = player.getEntityData();
            if (var2[0].equals("set"))
            {
                if (var2[1].equals("aggressive"))
                {
                    nbt.setBoolean("aggressive", true);
                    var1.addChatMessage(new ChatComponentText("Minions are set to aggressive"));
                }
                else if (var2[1].equals("passive"))
                {
                    nbt.setBoolean("aggressive", false);
                    var1.addChatMessage(new ChatComponentText("Minions are set to passive"));
                }
                else
                    throw new WrongUsageException("minion", new Object[0]);
            }
            else if (var2[0].equals("friend"))
            {
                nbt.setString(var2[1], "friend");
                var1.addChatMessage(new ChatComponentText(var2[1] + " is now a friend"));
            }
            else if (var2[0].equals("enemy"))
            {
                nbt.setString(var2[1], "enemy");
                var1.addChatMessage(new ChatComponentText(var2[1] + " is now an enemy"));
            }
            else
                throw new WrongUsageException("minion", new Object[0]);
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        List<String> result = new ArrayList<String>();
        if (par2ArrayOfStr.length == 1)
        {
            result.add("set");
            result.add("friend");
            result.add("enemy");
        }
        else if (par2ArrayOfStr.length == 2)
        {
            if (par2ArrayOfStr[0].equals("set"))
            {
                result.add("aggressive");
                result.add("passive");
            }
            else if (par2ArrayOfStr[0].equals("friend") || par2ArrayOfStr[0].equals("enemy"))
            {
                for (int i = 0; i < getPlayers().length; i++)
                {
                    result.add(getPlayers()[i]);
                }
            }
        }
        return result;
    }

    /**
     * Returns true if the given command sender is allowed to use this command.
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
        return par1ICommandSender instanceof EntityPlayer;
    }

    private String[] getPlayers()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof CommandBase)
        {
            return ((CommandBase) o).getCommandName().compareTo(getCommandName());
        }

        return 0;
    }

}
