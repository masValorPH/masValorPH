@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id){
        Optional<Article> optionalArticle = articleRepository.findById();
        if (optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article){
        articleRepository.save(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id, @RequestBody Article updatedArticle){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            article.setTitle(updatedArticle.getTitle());
            article.setContent(updatedArticle.getContent());
            article.setCategory(updatedArticle.getCategory());
            articleRepository.save(article);
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        articleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}