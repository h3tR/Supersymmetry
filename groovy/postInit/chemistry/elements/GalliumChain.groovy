import static globals.Globals.*

ELECTROLYZER = recipemap('electrolyzer')
BATCH_REACTOR = recipemap('batch_reactor')
VACUUM_DT = recipemap('vacuum_distillation')
DT = recipemap('distillation_tower')
DISTILLERY = recipemap('distillery')
CRYSTALLIZER = recipemap('crystallizer')
CVD = recipemap('cvd')
FORMINGPRESS = recipemap('forming_press')
ZONEREFINER = recipemap('zone_refiner')
MACERATOR = recipemap('macerator')
CUTTER = recipemap('cutter')

//FROM BAUXITE
ELECTROLYZER.recipeBuilder()
.fluidInputs(fluid('impure_soda_ash_solution') * 1000)
.notConsumable(ore('stickSteel'))
.notConsumable(metaitem('graphite_electrode'))
.fluidOutputs(fluid('soda_ash_solution') * 1000)
.chancedOutput(metaitem('dustGallium'), 500, 0)
.duration(300)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

ELECTROLYZER.recipeBuilder()
.fluidInputs(fluid('impure_sodium_hydroxide_solution') * 1000)
.notConsumable(ore('stickSteel'))
.notConsumable(metaitem('graphite_electrode'))
.fluidOutputs(fluid('sodium_hydroxide_solution') * 1000)
.chancedOutput(metaitem('dustGallium'), 500, 0)
.duration(300)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

//FROM SPHALERITE (WILL ADD LATER)

//FROM COAL FLY ASH (WILL ADD LATER)

//HIGH PURITY GALLIUM
BATCH_REACTOR.recipeBuilder()
.inputs(ore('dustGallium'))
.fluidInputs(fluid('chlorine') * 3500)
.outputs(metaitem('dustCrudeGalliumTrichloride') * 3)
.duration(120)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

//CHLORIDE ROUTE (75%)
DISTILLERY.recipeBuilder()
.fluidInputs(fluid('crude_gallium_trichloride') * 432)
.fluidOutputs(fluid('gallium_trichloride') * 750)
.duration(120)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

DT.recipeBuilder()
.fluidInputs(fluid('crude_gallium_trichloride') * 432)
.fluidOutputs(fluid('gallium_trichloride') * 1000)
.duration(120)
.EUt(Globals.voltAmps[2])
.buildAndRegister()

// Small Pile of Mercury II Chloride Dust * 1
mods.gregtech.distillery.removeByInput(30, [metaitem('circuit.integrated').withNbt(["Configuration": 1])], [fluid('crude_gallium_trichloride') * 432])

BATCH_REACTOR.recipeBuilder()
.fluidInputs(fluid('gallium_trichloride') * 1000)
.fluidInputs(fluid('hydrogen') * 3000)
.outputs(metaitem('dustHighPurityGallium'))
.fluidOutputs(fluid('hydrogen_chloride') * 3000)
.duration(120)
.EUt(Globals.voltAmps[2])
.buildAndRegister()

ELECTROLYZER.recipeBuilder()
.notConsumable(metaitem('stickIron'))
.notConsumable(metaitem('graphite_electrode'))
.notConsumable(fluid('water') * 1000)
.inputs(ore('dustMercuryIiChloride') * 3)
.fluidOutputs(fluid('chlorine') * 2000)
.fluidOutputs(fluid('mercury') * 1000)
.duration(120)
.EUt(Globals.voltAmps[2])
.buildAndRegister()

//VACUUM ROUTE
VACUUM_DT.recipeBuilder()
.inputs(ore('dustGallium'))
.outputs(metaitem('dustHighPurityGallium'))
.duration(120)
.EUt(Globals.voltAmps[2])
.buildAndRegister()

//GALLIUM ARSENIDE PRODUCTION (LIQUID ENCAPSULATED CZOCHIRALSKI PROCESS)
mods.gregtech.mixer.removeByInput(7, [metaitem('dustGallium'), metaitem('dustArsenic'), metaitem('circuit.integrated').withNbt(["Configuration": 1])], null)
//SEED CRYSTAL
CRYSTALLIZER.recipeBuilder()
.inputs(ore('dustSmallHighPurityArsenic'))
.fluidInputs(fluid('high_purity_gallium') * 250)
.chancedOutput(metaitem('seed_crystal.gallium_arsenide'), 2500, 0)
.duration(500)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

//PBN CRUCIBLE
CVD.recipeBuilder()
.fluidInputs(fluid('boron_trichloride') * 1000)
.fluidInputs(fluid('ammonia') * 1000)
.outputs(metaitem('dustBoronNitride') * 2)
.fluidOutputs(fluid('hydrogen_chloride') * 3000)
.duration(120)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

FORMINGPRESS.recipeBuilder()
.inputs(ore('dustBoronNitride') * 14)
.notConsumable(metaitem('shape.mold.crucible'))
.outputs(metaitem('crucible.boron.nitride'))
.duration(500)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

//CRYSTALLIZATION
CRYSTALLIZER.recipeBuilder()
.circuitMeta(1)
.fluidInputs(fluid('high_purity_gallium') * 144)
.fluidInputs(fluid('high_purity_arsenic') * 144)
.notConsumable(fluid('boron_trioxide') * 720)
.inputs(metaitem('seed_crystal.gallium_arsenide'))
.notConsumable(metaitem('crucible.boron.nitride'))
.outputs(metaitem('unrefined_boule.gallium_arsenide'))
.duration(240)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

CRYSTALLIZER.recipeBuilder()
.circuitMeta(2)
.fluidInputs(fluid('high_purity_gallium') * 144)
.fluidInputs(fluid('high_purity_arsenic') * 144)
.notConsumable(fluid('boron_trioxide') * 720)
.notConsumable(metaitem('seed_crystal.gallium_arsenide'))
.notConsumable(metaitem('crucible.boron.nitride'))
.outputs(metaitem('ingotGalliumArsenide'))
.duration(240)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

ZONEREFINER.recipeBuilder()
.inputs(metaitem('unrefined_boule.gallium_arsenide'))
.outputs(metaitem('boule.gallium_arsenide'))
.duration(120)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

CUTTER.recipeBuilder()
        .fluidInputs(fluid('ultrapure_water') * 100)
        .inputs(metaitem('boule.gallium_arsenide'))
        .outputs(metaitem('wafer.gallium_arsenide') * 16)
        .outputs(metaitem('seed_crystal.gallium_arsenide'))
        .duration(400)
        .EUt(64)
        .buildAndRegister()