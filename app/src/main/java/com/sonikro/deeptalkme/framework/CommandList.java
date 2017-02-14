package com.sonikro.deeptalkme.framework;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jonathan Nagayoshi on 5/07/2016.
 */
public class CommandList {
    private ArrayList<ActivityCommand> commandList;

    public CommandList()
    {
        setCommandList(new ArrayList<ActivityCommand>());
    }

    public void add(ActivityCommand command)
    {
        getCommandList().add(command);
    }

    public void addFirst(ActivityCommand command)
    {
        getCommandList().add(0,command);
    }

    public void remove(ActivityCommand command)
    {
        getCommandList().remove(command);
    }

    public void addAll(CommandList inputCommandList)
    {
        this.commandList.addAll(inputCommandList.getCommandList());
    }
    public void removeAll(CommandList inputCommandList)
    {
        this.commandList.removeAll(inputCommandList.getCommandList());
    }

    public Iterator<ActivityCommand> getIterator()
    {
        return getCommandList().iterator();
    }

    public int indexOf(ActivityCommand command)
    {
        return getCommandList().indexOf(command);
    }

    public int getCommandIndex(ActivityCommand fromCommand)
    {
        for(int i = 0; i < getCommandList().size(); i++)
        {
            if(getCommandList().get(i) == fromCommand)
            {
                return i;
            }
        }
        return -1;
    }

    public ActivityCommand get(int index)
    {
        return getCommandList().get(index);
    }

    public int size()
    {
        return getCommandList().size();
    }

    public void clear()
    {
        getCommandList().clear();
    }

    protected ArrayList<ActivityCommand> getCommandList() {
        return commandList;
    }

    protected void setCommandList(ArrayList<ActivityCommand> commandList) {
        this.commandList = commandList;
    }
}
