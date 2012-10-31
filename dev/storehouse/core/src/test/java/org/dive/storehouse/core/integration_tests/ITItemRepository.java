package org.dive.storehouse.core.integration_tests;

import static org.fest.assertions.Assertions.*;

import java.util.Collection;

import javax.inject.Inject;

import org.dive.storehouse.core.Item;
import org.dive.storehouse.core.ItemRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Created 31.10.2012
 * @author orionll
 *
**/
public class ITItemRepository extends Arquillian
{
    private static final Logger LOG = LoggerFactory.getLogger(ITItemRepository.class);

    @Inject
    private ItemRepository itemRepository;

    @Deployment
    public static Archive<?> createDeployment()
    {
        JavaArchive coreJar = ShrinkWrap.create(JavaArchive.class);
        coreJar.addPackage("org.dive.storehouse.core");
        coreJar.addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
        coreJar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class);
        resolver.loadMetadataFromPom("pom.xml");

        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war");
        war.addAsLibrary(coreJar);
        war.addAsLibraries(resolver.artifact("org.apache.commons:commons-lang3").resolveAsFiles());
        war.addAsLibraries(resolver.artifact("com.google.guava:guava").resolveAsFiles());
        war.addAsLibraries(resolver.artifact("org.easytesting:fest-assert").resolveAsFiles());

        LOG.info("Created web archive: {}", war.toString(Formatters.VERBOSE));

        return war;
    }

    @Test
    public void persistAndFindById()
    {
        Item item = new Item();
        item.setName("Test");
        this.itemRepository.persist(item);
        assertThat(item.getId()).isNotNull();

        Item foundItem = this.itemRepository.findById(item.getId());
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    public void findAll()
    {
        Collection<Item> itemsBeforeInsertion = this.itemRepository.findAll();
        assertThat(itemsBeforeInsertion).isNotNull();
    }
}
