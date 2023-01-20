# CheckMoney-Expansion
This is a [PlaceholderAPI](https://links.alonsoaliaga.com/PlaceholderAPI) expansion to allow owners/configurator check for balance in one placeholder.

# Installation
You can install this expansion in 2 ways:
### 1) PlaceholderAPI eCloud (Available ✔️)
While being in console or having OP run the following commands:
> /papi ecloud download checkmoney\
> /papi reload

✅ Expansion is ready to be used!
### 2) Manual download
Go to [eCloud](https://api.extendedclip.com/expansions/checkmoney/) and click `Download Latest` button to get the .jar file.\
Copy and paste the file in `/plugins/PlaceholderAPI/expansions/` and run:
> /papi reload

✅ Expansion is ready to be used!
# Placeholders
The following placeholders are available:
> ###  %checkmoney_hasenough_{price}_Yes output_No output%
> Allows you to return custom message if player has the required amount or not. <br>
> Supports PlaceholderAPI but requires `{ }` instead of `% %`.<br>
> 
> :warning: This is especially useful when using menu plugins like [DeluxeMenu](https://alonsoaliaga.com/DeluxeMenus) to be able
> to check if player has money or not and use it in the item lore.
> 
> **Example:** %checkmoney_hasenough_100_&2✓_&c✘%<br>
> **Output:** If the player has 100 in his balance it will return `&2✓`, otherwise it will return `&c✘`.

> ###  %checkmoney_hasenoughcustom_{money_placeholder}_{price}_Yes output_No output%
> Allows you to return custom message if player has the required amount or not. <br>
> This is different from the one above as this DOES NOT hook with vault. <br>
> You can provide the placeholder you want to be taken as player's balance. <br>
> Remember the placeholder MUST RETURN a number/decimal, this means no commas, letters, etc. <br>
> Supports PlaceholderAPI but requires `{ }` instead of `% %`.<br>
>
> :warning: This is especially useful when using menu plugins like [DeluxeMenu](https://alonsoaliaga.com/DeluxeMenus) to be able
> to check if player has money or not and use it in the item lore.
>
> **Example:** %checkmoney_hasenoughcustom_{placeholder_for_balance}_100_&2✓_&c✘%<br>
> **Output:** If value returned from `{placeholder_for_balance}` is greater or equal than 100 it will return `&2✓`, otherwise it will return `&c✘`.

# Want more cool and useful expansions?
<p align="center">
    <a href="https://alonsoaliaga.com/moregradients">MoreGradients Expansion</a><br>
    Customize texts a bit more with cool gradient styles, support custom and iridium!<br>
    <br>
    <a href="https://alonsoaliaga.com/capitalize">Capitalize Expansion</a><br>
    Customize texts a bit more removing underscores, dashes and capitalizing letters!<br>
    <br>
    <a href="https://alonsoaliaga.com/checkdate">CheckDate Expansion</a><br>
    Check if server/machine date is the desired one with custom output! (Specially for messages)<br>
</p>

# Want more tools?
**Make sure to check our [BRAND NEW TOOL](https://alonsoaliaga.com/hex) to generate your own text with gradients!**<br>
<p align="center">
    <a href="https://alonsoaliaga.com/hex"><img src="https://i.imgur.com/766Es8I.png" alt="Our brand new tool!"/></a>
</p>

# Do you have a cool expansion idea?
<p align="center">
    <a href="https://alonsoaliaga.com/discord">Join us on Discord</a><br>
    <a href="https://alonsoaliaga.com/discord"><img src="https://i.imgur.com/2pslxIN.png"></a><br>
    Let us know what's your idea and it might become true!
</p>

# Questions?
<p align="center">
    <a href="https://alonsoaliaga.com/discord"><img style="width:200px;" src="https://i.imgur.com/laEFHcG.gif"></a><br>
    <a href="https://alonsoaliaga.com/discord"><span style="font-size:25px;font-weight:bold;color:rgb(100,100,255);">Join us in our discord!</span></a>
</p>