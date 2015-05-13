package com.massivecraft.massivefx.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.ArgSetting;
import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivefx.P;
import com.massivecraft.massivefx.Permission;
import com.massivecraft.massivefx.selector.Selector;

public class CmdFxSelectorList extends MassiveCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFxSelectorList()
	{
		// Aliases
		this.addAliases("sl", "selectorlist");
		
		// Args
		this.addArg(ArgSetting.getPage());
		
		// Requirements
		this.addRequirements(new ReqHasPerm(Permission.SELECTOR_LIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		int page = this.readArg();
		
		List<String> lines = new ArrayList<String>();
		lines.add("<a># <i>There is one selector per line in this list.");
		lines.add("<a># <i>Selectors have data after a colon like sphere:2");
		lines.add("<a># <i>Chain selectors using dots like there.sphere:3");
		
		for (Selector selector: P.p.getSelectors())
		{
			StringBuilder sb = new StringBuilder();
			
			sb.append("<h>");
			sb.append(selector.getId());
			
			sb.append(" <i>");
			sb.append(selector.getDescription());
			
			if (selector.getExample() != null)
			{
				sb.append(" <aqua>");
				sb.append(selector.getExample());
			}
			
			lines.add(sb.toString());
		}
		
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, page, "Available Selectors", sender));
	}
	
}