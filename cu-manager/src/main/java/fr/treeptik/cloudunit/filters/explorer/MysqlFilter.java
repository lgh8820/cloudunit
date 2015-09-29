/*
 * LICENCE : CloudUnit is available under the Gnu Public License GPL V3 : https://www.gnu.org/licenses/gpl.txt
 *     but CloudUnit is licensed too under a standard commercial license.
 *     Please contact our sales team if you would like to discuss the specifics of our Enterprise license.
 *     If you are not sure whether the GPL is right for you,
 *     you can always test our software under the GPL and inspect the source code before you contact us
 *     about purchasing a commercial license.
 *
 *     LEGAL TERMS : "CloudUnit" is a registered trademark of Treeptik and can't be used to endorse
 *     or promote products derived from this project without prior written permission from Treeptik.
 *     Products or services derived from this software may not be called "CloudUnit"
 *     nor may "Treeptik" or similar confusing terms appear in their names without prior written permission.
 *     For any questions, contact us : contact@treeptik.fr
 */

package fr.treeptik.cloudunit.filters.explorer;

/**
 * Created by nicolas on 08/06/15.
 */

import fr.treeptik.cloudunit.json.ui.FileUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nicolas on 08/06/15.
 */
public class MysqlFilter
                implements ExplorerFilter
{

    private final Logger logger = LoggerFactory.getLogger( MysqlFilter.class );

    @Override
    public boolean isValid( FileUnit fileUnit )
    {

        // On laisse passer tous les fichiers.
        // la sélection a été fait en aval
        if ( !fileUnit.isDir() )
            return true;

        if ( logger.isDebugEnabled() )
        {
            logger.debug( "--> fileUnit : " + fileUnit.getBreadcrump() );
        }

        String breadcrumb = fileUnit.getBreadcrump();
        if ( breadcrumb.equalsIgnoreCase( "/cloudunit" )
                        || breadcrumb.startsWith( "/cloudunit/backup" )
                        || breadcrumb.startsWith( "/cloudunit/tmp" )
                        || breadcrumb.equalsIgnoreCase( "/etc" )
                        || breadcrumb.equalsIgnoreCase( "/var" )
                        || breadcrumb.startsWith( "/etc/mysql" )
                        || breadcrumb.startsWith( "/var/log" ) )
        {
            return true;
        }

        return false;
    }

    @Override
    public void isRemovable( FileUnit fileUnit )
    {

        if ( logger.isDebugEnabled() )
        {
            logger.debug( "--> fileUnit : " + fileUnit.getBreadcrump() );
        }

        String breadcrumb = fileUnit.getBreadcrump();
        if ( breadcrumb.equalsIgnoreCase( "/cloudunit" )
                        || breadcrumb.equalsIgnoreCase( "/cloudunit/backup" )
                        || breadcrumb.equalsIgnoreCase( "/cloudunit/tmp" )
                        || breadcrumb.equalsIgnoreCase( "/etc" )
                        || breadcrumb.equalsIgnoreCase( "/etc/mysql" ) )
        {
            fileUnit.removable( false );
        }
        else
        {
            fileUnit.removable( true );
        }
    }

    @Override
    public void isSafe( FileUnit fileUnit )
    {
        if ( logger.isDebugEnabled() )
        {
            logger.debug( "--> fileUnit : " + fileUnit.getBreadcrump() );
        }

        String breadcrumb = fileUnit.getBreadcrump();
        if ( breadcrumb.equalsIgnoreCase( "/cloudunit" )
                        || breadcrumb.equalsIgnoreCase( "/cloudunit/backup" )
                        || breadcrumb.equalsIgnoreCase( "/cloudunit/appconf" )
                        || breadcrumb.equalsIgnoreCase( "/cloudunit/binaries/lib" ) )
        {
            fileUnit.safe( true );
        }
        else
        {
            fileUnit.safe( false );
        }
    }
}
