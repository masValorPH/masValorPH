@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        Optional<Role> optionalRole = roleRepository.findById();
        if (optionalRole.isPresent()){
            Role role = optionalRole.get();
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Role createRole(@RequestBody Role role){
        roleRepository.save(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVarible("id") Long id, @RequestBody Role updatedRole){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role role = optionalRole.get();
            role.setName(updatedRole.getName());
            roleRepository.save(role);
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariabl("id") Long id) {
        roleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}